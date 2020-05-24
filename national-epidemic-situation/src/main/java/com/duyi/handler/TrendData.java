package com.duyi.handler;


import com.duyi.bean.Trend;
import com.duyi.service.BaseTrendService;
import com.duyi.util.HttpUrlConnection;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cr
 */
@Component
public class TrendData {
    @Autowired
    BaseTrendService trendService;
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    public static List<Trend> getData(){
        List<Trend> trends = new ArrayList<>();
        Gson gson = new Gson();
        String result = HttpUrlConnection.getData(URL);
        Map map = gson.fromJson(result, Map.class);
        String str = (String) map.get("data");
        Map dataMap = gson.fromJson(str,Map.class);
        ArrayList list = (ArrayList) dataMap.get("chinaDayList");
        for (Object o: list){
            Map mapList = (Map) o;
            double confirm = (double) mapList.get("confirm");
            double heal = (double) mapList.get("heal");
            double dead = (double) mapList.get("dead");
            String data = (String) mapList.get("date");
            Trend trend = new Trend((int)confirm,(int)heal,(int)dead,data);
            trends.add(trend);
        }
        return trends;
    }
    @PostConstruct
    public void saveData(){
        List<Trend> trends = getData();
        trendService.remove(null);
        trendService.saveBatch(trends);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ?")
    public void updateData(){
        saveData();
    }
}
