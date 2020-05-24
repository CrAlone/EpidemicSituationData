package com.duyi.handler;

import com.duyi.bean.ChinaDayList;
import com.duyi.service.BaseChinaDayListService;
import com.duyi.util.HttpUrlConnectionUtil;
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
public class ChinaDayAddList {
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";

    @Autowired
    private BaseChinaDayListService service;

    public static List<ChinaDayList> read(){
        List<ChinaDayList> chinaList = new ArrayList<>();

        String dataResult = HttpUrlConnectionUtil.doGit(URL);

        //创建一个Gson将gson数据转换成集合对象
        Gson gson = new Gson();

        Map map = gson.fromJson(dataResult, Map.class);
        String list = (String) map.get("data");

        Map listMap = gson.fromJson(list,Map.class);
        ArrayList china = (ArrayList) listMap.get("chinaDayAddList");

        for (Object o : china){
            Map m = (Map) o;
            double confirm = (double) m.get("confirm");
            double suspect = (double) m.get("suspect");

            String data = (String) m.get("date");

            ChinaDayList list1 = new ChinaDayList((int)confirm,(int)suspect,data);

            chinaList.add(list1);
    }
        return chinaList;
    }

    @PostConstruct
    public void saveData(){
        List<ChinaDayList> chinaList = read();
        service.remove(null);
        service.saveBatch(chinaList);
    }

    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
}
