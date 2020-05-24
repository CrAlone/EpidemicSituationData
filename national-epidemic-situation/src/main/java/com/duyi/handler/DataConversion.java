package com.duyi.handler;
import com.duyi.bean.ChinaData;
import com.duyi.service.BaseEpidemic;
import com.duyi.util.HttpUrlConnection;
import com.duyi.util.HttpUrlConnectionUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author cr
 */
@Component
public class DataConversion {
    /**
     * 设计一个属性为固定的url地址
     */
    private static final String URL_STRING = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    @Autowired
    private BaseEpidemic epidemic;
    @PostConstruct
    public void saveData(){
        try {
            List<ChinaData> data = read();
            epidemic.remove(null);
            epidemic.saveBatch(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
    public static List<ChinaData> read(){

//        //创建一个I/O类读取文件
//        FileReader reader = new FileReader("data.txt");
//        //创建一个char数组用来读取i/o中的数据
//        //每次的数据读取1024个
//        char[] pond = new char[1024];
//        //创建一个int类型的变量 记录数据读取的位置
//        int count ;
//        //创建一个builder用来拼接数据
//        StringBuilder builder = new StringBuilder();
//        while ((count = reader.read(pond)) >0){
//            builder.append(pond,0,count);
//        }
        //改为实时更新的数据
        String dataResult = HttpUrlConnectionUtil.doGit(URL_STRING);
        //用来找寻当天城市的新增和减少
        String compare = HttpUrlConnection.getData(URL);
        //创建一个Gson将gson数据转换成集合对象
        Gson gson = new Gson();
        Map map = gson.fromJson(dataResult,Map.class);
        Map compareMap = gson.fromJson(compare,Map.class);
        //找到对应的key，寻找我们需要的一个字符串
        String str = (String) map.get("data");
        String s = (String) compareMap.get("data");
        Map strMap = gson.fromJson(str,Map.class);
        Map sMap = gson.fromJson(s,Map.class);
        //获取其中中国的数据
        ArrayList areaTree = (ArrayList) strMap.get("areaTree");
        Map provinceCompare = (Map) sMap.get("provinceCompare");
        Iterator it = provinceCompare.keySet().iterator();

        Map dataMap = (Map) areaTree.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");
        List<ChinaData> result = new ArrayList<>();

            while (it.hasNext()){
                String area = (String) it.next();
                for (Object obj : childrenList) {
                    Map data = (Map) obj;
                    //地区名
                    String name = (String) data.get("name");
                if (name.equals(area)){
                    Map nameMap = (Map) provinceCompare.get(name);
                    double addAndCut = (double) nameMap.get("nowConfirm");
                    double dayNum = (double) nameMap.get("zero");
                    Map totalMap = (Map) data.get("total");
                    //现有确诊
                    double nowConfirm = (double) totalMap.get("nowConfirm");
                    //累计确诊
                    double confirm = (double) totalMap.get("confirm");
                    //治愈
                    double heal = (double) totalMap.get("heal");
                    //死亡
                    double dead = (double) totalMap.get("dead");
                    ChinaData chinaData = new ChinaData(name, (int)nowConfirm, (int)confirm, (int)heal, (int)dead,(int)addAndCut,(int)dayNum);
                    result.add(chinaData);
                }
            }
        }
        return result;
    }
}
