package com.duyi.handler;

import com.duyi.bean.ChinaData;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cr
 */
@Component
public class JsoupHandler {
    private static final String URL_STRING = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline&isappinstalled=0";
//    @Autowired
//    private BaseEpidemic2 epidemic2;
//    @PostConstruct
//    public void saveData(){
//        try {
//            List<ChinaData> list = doGit();
//            epidemic2.remove(null);
//            epidemic2.saveBatch(list);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
//    public void updateData(){
//        saveData();
////    }
//    public static List<ChinaData> doGit(){
//        //通过本地html获取其中的类容
////        Document document = Jsoup.parse('html文件类容');
//        //通过连接地址获取一个html
//        Document document = null;
//        try {
//            document = Jsoup.connect(URL_STRING).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //也可以通过id和名字找到相对应的类容
////        Element element = document.getElementById("id");
////        Element element = document.getElementsByTag("name");
//        //获取文件html中所有的script标签
//        // Elements scripts = document.select("script");
//        //通过id获取对应的script标签
//        List<ChinaData> dataList = new ArrayList<>();
//        try {
//            assert document != null;
//            Element element = document.getElementById("getAreaStat");
//            //将element对象转换成String字符串
//            String data = element.data();
//            //截取不需要的类容
//            String result = data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
//            //通过gson转换成list集合
//            Gson gson = new Gson();
//            ArrayList list = gson.fromJson(result,ArrayList.class);
//
//            for (Object o : list) {
//                Map map = (Map) o;
//                String name = (String) map.get("provinceName");
//                double nowConfirm = (double) map.get("currentConfirmedCount");
//                double confirm = (double) map.get("confirmedCount");
//                double heal = (double) map.get("curedCount");
//                double dead = (double) map.get("deadCount");
//                ChinaData chinaData = new ChinaData(name, (int) nowConfirm, (int) confirm, (int) heal, (int) dead);
//                dataList.add(chinaData);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return dataList;
//    }
}
