package com.duyi.handler;

import com.duyi.bean.Curve;
import com.duyi.service.BaseCurve;
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
public class DataImg {
    @Autowired
    private BaseCurve curve;
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    public static List<Curve> getData(){
        List<Curve> res = new ArrayList<>();
        String result = HttpUrlConnection.getData(URL);
        Gson gson = new Gson();
        Map map = gson.fromJson(result, Map.class);
        String str = (String) map.get("data");
        Map dataMap = gson.fromJson(str,Map.class);
        ArrayList list = (ArrayList) dataMap.get("chinaDayList");
        for (Object o : list){
            Map mapList = (Map) o;
            String data = (String) mapList.get("date");
            double nowConfirm = (double) mapList.get("nowConfirm");
            Curve curve = new Curve(data,(int)nowConfirm);
            res.add(curve);
        }
        return res;
    }

    @PostConstruct
    public void saveData(){
        List<Curve> result = getData();
        curve.remove(null);
        curve.saveBatch(result);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
}
