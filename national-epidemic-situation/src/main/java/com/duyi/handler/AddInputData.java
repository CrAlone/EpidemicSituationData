package com.duyi.handler;

import com.duyi.bean.AddInput;
import com.duyi.service.BaseAddInput;
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
public class AddInputData {
    private static final String UTL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    @Autowired
    private BaseAddInput addInput;
    public List<AddInput> doGit(){
        List<AddInput> list = new ArrayList<>();
        String result = HttpUrlConnection.getData(UTL);
        Gson gson = new Gson();
        Map map = gson.fromJson(result, Map.class);
        String s = (String) map.get("data");
        Map m = gson.fromJson(s,Map.class);
        ArrayList arrayList = (ArrayList) m.get("chinaDayAddList");
        for (Object o :arrayList){
            Map dataMap = (Map) o;
            double importedCase = (double) dataMap.get("importedCase");
            if ((int)importedCase > 0){
                String data = (String) dataMap.get("date");
                double num = (double) dataMap.get("importedCase");
                AddInput addInput = new AddInput(data,(int)num);
                list.add(addInput);
            }
        }
        return list;
    }

    @PostConstruct
    public void saveData(){
        List<AddInput> list = doGit();
        addInput.remove(null);
        addInput.saveBatch(list);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }
}
