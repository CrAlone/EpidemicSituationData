package com.duyi.handler;

import com.duyi.bean.Input;
import com.duyi.service.BaseInput;
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
public class InputData {
    @Autowired
    private BaseInput input;
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    public List<Input> getData(){
        List<Input> list = new ArrayList<>();
        String result = HttpUrlConnection.getData(URL);
        Gson gson = new Gson();
        Map map = gson.fromJson(result,Map.class);
        String s = (String) map.get("data");
        Map dataMap = gson.fromJson(s,Map.class);
        ArrayList arrayList = (ArrayList) dataMap.get("chinaDayList");
        for (Object o : arrayList){
            Map m = (Map) o;
            double importedCase = (double) m.get("importedCase");
            if ((int)importedCase > 0){
                String date = (String) m.get("date");
                double num = (double) m.get("importedCase");
                Input input = new Input(date,(int)num);
                list.add(input);
            }
        }
        return list;
    }
    @PostConstruct
    public void saveData(){
        List<Input> list = getData();
        input.remove(null);
        input.saveBatch(list);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
}
