package com.duyi.util;
import com.duyi.bean.ChinaData;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * @author cr
 */
public class DataConversion {
    public static void main(String[] args) throws Exception {
        List<ChinaData> read = read();
        System.out.println(read);
    }
    /**
     *
     * @throws Exception 如果没有找到文件则跑出异常
     */
    private static List<ChinaData> read()throws Exception{
        //创建一个I/O类读取文件
        FileReader reader = new FileReader("data.txt");
        //创建一个char数组用来读取i/o中的数据
        //每次的数据读取1024个
        char[] pond = new char[1024];
        //创建一个int类型的变量 记录数据读取的位置
        int count ;
        //创建一个builder用来拼接数据
        StringBuilder builder = new StringBuilder();
        while ((count = reader.read(pond)) >0){
            builder.append(pond,0,count);
        }
        //创建一个Gson将json数据转换成集合对象
        Gson gson = new Gson();
        Map map = gson.fromJson(builder.toString(),Map.class);
        //获取其中中国的数据
        ArrayList areaTree = (ArrayList) map.get("areaTree");
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
