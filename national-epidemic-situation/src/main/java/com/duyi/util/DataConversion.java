package com.duyi.util;
import com.duyi.bean.ChinaData;
import com.google.gson.Gson;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * @author cr
 */
public class DataConversion {
    /**
     * 设计一个属性为固定的url地址
     */
    public static final String URL_STRING = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
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
        //创建一个Gson将gson数据转换成集合对象
        Gson gson = new Gson();
        Map map = gson.fromJson(dataResult,Map.class);
        //找到对应的key，寻找我们需要的一个字符串
        String str = (String) map.get("data");
        Map strMap = gson.fromJson(str,Map.class);
        //获取其中中国的数据
        ArrayList areaTree = (ArrayList) strMap.get("areaTree");
        Map dataMap = (Map) areaTree.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");
        List<ChinaData> result = new ArrayList<>();
        for (Object obj : childrenList) {
            Map data = (Map) obj;
            //地区名
            String name = (String) data.get("name");
            Map totalMap = (Map) data.get("total");
            //现有确诊
            double nowConfirm = (double) totalMap.get("nowConfirm");
            //累计确诊
            double confirm = (double) totalMap.get("confirm");
            //治愈
            double heal = (double) totalMap.get("heal");
            //死亡
            double dead = (double) totalMap.get("dead");
            ChinaData chinaData = new ChinaData(name, (int)nowConfirm, (int)confirm, (int)heal, (int)dead);
            result.add(chinaData);
        }
        return result;
    }
}
