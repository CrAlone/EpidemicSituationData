package com.duyi.handler;

import com.duyi.bean.Statistics;
import com.duyi.service.BaseStatisticsService;
import com.duyi.util.HttpUrlConnection;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**国内总统计
 * @author cr
 */
@Component
public class StatisticsData {
    @Autowired
    private BaseStatisticsService statisticsService;
    private static final String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
    public static Statistics doGit(){
        String result = HttpUrlConnection.getData(URL);
        Gson gson = new Gson();
        Map dataMap = gson.fromJson(result,Map.class);
        String strData = (String) dataMap.get("data");
        Map map = gson.fromJson(strData,Map.class);
        Map chinaTotal = (Map) map.get("chinaTotal");
        //统计截止时间
        String lastUpdateTime = (String) map.get("lastUpdateTime");
        //累计确证
        double confirm = (double) chinaTotal.get("confirm");
        //累计治愈
        double heal = (double) chinaTotal.get("heal");
        //累计死亡
        double dead = (double) chinaTotal.get("dead");
        //现有确证
        double nowConfirm = (double) chinaTotal.get("nowConfirm");
        //无症状感染者
        double noInfect = (double) chinaTotal.get("noInfect");
        //境外输入
        double importedCase = (double) chinaTotal.get("importedCase");
        return new Statistics((int)confirm,(int)heal,(int)dead,(int)nowConfirm,(int)noInfect,(int)noInfect,lastUpdateTime);
    }
    @PostConstruct
    public void saveData(){
        Statistics statistics = doGit();
        statisticsService.remove(null);
        statisticsService.save(statistics);
    }
    @Scheduled(cron = "0/2 0 0 1/1 * ? ")
    public void updateData(){
        saveData();
    }


}
