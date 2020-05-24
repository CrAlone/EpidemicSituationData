package com.duyi.handler;

import com.duyi.bean.Existing;
import com.duyi.service.BaseExisting;
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
public class ExistingData {
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    @Autowired
    private BaseExisting existing;
    public static List<Existing> doGie(){
        List<Existing> list = new ArrayList<>();
        String result = HttpUrlConnection.getData(URL);
        Gson gson = new Gson();
        Map map = gson.fromJson(result, Map.class);
        String data = (String) map.get("data");
        Map dataMap = gson.fromJson(data,Map.class);
        Map m  = (Map) dataMap.get("nowConfirmStatis");
        double gat = (double) m.get("gat");
        String g = "港澳台病例:"+gat+"例";
        double imp = (double) m.get("import");
        String i = "境外输入病例:"+imp+"例";
        double province = (double) m.get("province");
        String p = "31省本土病例:"+province+"例";
        Existing existing = new Existing(g,(int)gat);
        Existing existing2 = new Existing(i,(int)imp);
        Existing existing3 = new Existing(p,(int)province);
        list.add(existing);
        list.add(existing2);
        list.add(existing3);
        return list;
    }
    @PostConstruct
    public void saveData(){
        List<Existing> list = doGie();
        existing.remove(null);
        existing.saveBatch(list);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
}
