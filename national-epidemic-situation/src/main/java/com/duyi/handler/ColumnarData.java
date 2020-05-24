package com.duyi.handler;

import com.duyi.bean.Overseas;
import com.duyi.service.BaseOverseas;
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
public class ColumnarData {
    @Autowired
    private BaseOverseas overseas;
    @PostConstruct
    public void saveData(){
        List<Overseas> list = getData();
        overseas.remove(null);
        overseas.saveBatch(list);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
    public List<Overseas> getData(){
        List<Overseas> list = new ArrayList<>();
        String result = HttpUrlConnection.getData(URL);
        Gson gson = new Gson();
        Map map = gson.fromJson(result, Map.class);
        String str = (String) map.get("data");
        Map dataMap = gson.fromJson(str,Map.class);
        ArrayList areaTree = (ArrayList) dataMap.get("areaTree");
        Map m = (Map) areaTree.get(0);
        ArrayList list1 = (ArrayList) m.get("children");
        for (Object o : list1){
            Map subMap = (Map) o;
            String name = (String) subMap.get("name");
            ArrayList children = (ArrayList) subMap.get("children");
            for (Object i : children){
                Map ma = (Map) i;
                if ("境外输入".equals(ma.get("name"))){
                    Map total = (Map) ma.get("total");
                    double tip = (double) total.get("confirm");
                    Overseas overseas = new Overseas(name,(int)tip);
                    list.add(overseas);
                }
            }
        }
        return list;
    }
}
