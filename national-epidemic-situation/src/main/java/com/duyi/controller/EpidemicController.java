package com.duyi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.duyi.bean.*;

import com.duyi.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cr
 */
@Controller
@RequestMapping("/Epidemic")
public class EpidemicController {
    @Autowired
    private BaseEpidemic baseEpidemic;
    @Autowired
    private BaseCurve curve;
    @Autowired
    private BaseChinaDayListService service;
    @Autowired
    private BaseTrendService trendService;
    @Autowired
    private BaseOverseas overseas;
    @Autowired
    private BaseInput input;
    @Autowired
    private BaseAddInput addInput;
    @Autowired
    private BaseExisting existing;
    @Autowired
    private BaseStatisticsService statisticsService;
    @GetMapping
    public String chinaEpidemic(Model model, HttpSession session) {
        List<ChinaData> list = baseEpidemic.list();
        Person user = (Person) session.getAttribute("user");
        model.addAttribute("user",user);
        //将中国地图中的名字和确证人数的数据拆分出来并存储到另一个数据库
        List<ChinaMap> chinaList = new ArrayList<>();
        for (ChinaData cd : list){
            String name = cd.getArea();
            Integer nowConfirm = cd.getNowConfirm();
            ChinaMap chinaMap = new ChinaMap(name,nowConfirm);
            chinaList.add(chinaMap);
        }
        model.addAttribute("chinaList",new Gson().toJson(chinaList));
        model.addAttribute("datalist",list);
        //境外输入省市top10---------------------------------------------
        List<Overseas> list1 = overseas.list();
        Collections.sort(list1);
        List<String> area = new ArrayList<>();
        List<Integer> tip = new ArrayList<>();
        for (int i= 0;i<10;i++){
            area.add(list1.get(i).getArea());
            tip.add(list1.get(i).getTip());
        }
        //境外累计增加---------------------------------------------
        List<AddInput> addInputs = addInput.list();
        //将日期和人数拆分出来
        List<String> data2 = new ArrayList<>();
        List<Integer> importedCase = new ArrayList<>();
        for (AddInput put : addInputs){
            data2.add(put.getData());
            importedCase.add(put.getImportedCase());
        }
        //增加趋势---------------------------------------------
        List<Input> inputList = input.list();
        //将日期和人数拆分出来
        List<String> data3 = new ArrayList<>();
        List<Integer> importedCase2 = new ArrayList<>();
        for (Input i : inputList){
            data3.add(i.getData());
            importedCase2.add(i.getImportedCase());
        }
        //全国现有确证趋势图---------------------------------------------
        List<Curve> curveList = curve.list();
        //将list集合解析出来 分为data 和nowConfirm
        List<String> data4 = new ArrayList<>();
        List<Integer> nowConfirm  = new ArrayList<>();
        for (Curve curve : curveList){
            data4.add(curve.getData());
            nowConfirm.add(curve.getNowConfirm());
        }
        //全国现有确证趋势---------------------------------------------
        List<ChinaDayList> chinaDayLists = service.list();
        //将list拆分成确证人数和疑似确证
        List<Integer> confirm = new ArrayList<>();
        List<Integer> suspect = new ArrayList<>();
        List<String> data = new ArrayList<>();
        for (ChinaDayList dayList : chinaDayLists){
            confirm.add(dayList.getConfirm());
            suspect.add(dayList.getSuspect());
            data.add(dayList.getData());
        }
        //全国累计确证/治愈/死亡趋势折线图
        List<Trend> trendList= trendService.list();
        //将数据解析出来
        List<Integer> confirm2 = new ArrayList<>();
        List<Integer> heal = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();
        List<String> data5 = new ArrayList<>();
        for (Trend trend : trendList){
            confirm2.add(trend.getConfirm());
            heal.add(trend.getHeal());
            dead.add(trend.getDead());
            data5.add(trend.getData());
        }
        //全国现有确证构成:
        List<Existing> existings = existing.list();
        Collections.sort(existings);
        //将数据拆分 并添加总数
        int count = 0;
        for (Existing existing : existings){
            count += existing.getValue();
        }
        model.addAttribute("count",new Gson().toJson(count));
        model.addAttribute("existings",new Gson().toJson(existings));
        model.addAttribute("confirm2",new Gson().toJson(confirm2));
        model.addAttribute("heal",new Gson().toJson(heal));
        model.addAttribute("dead",new Gson().toJson(dead));
        model.addAttribute("data5",new Gson().toJson(data5));
        model.addAttribute("confirm",new Gson().toJson(confirm));
        model.addAttribute("suspect",new Gson().toJson(suspect));
        model.addAttribute("data",new Gson().toJson(data));
        model.addAttribute("data4",new Gson().toJson(data4));
        model.addAttribute("nowConfirm",new Gson().toJson(nowConfirm));
        model.addAttribute("data3",new Gson().toJson(data3));
        model.addAttribute("importedCase2",new Gson().toJson(importedCase2));
        model.addAttribute("data2",new Gson().toJson(data2));
        model.addAttribute("importedCase",new Gson().toJson(importedCase));
        model.addAttribute("area",new Gson().toJson(area));
        model.addAttribute("tip",new Gson().toJson(tip));
        //统计数据
        Statistics statistics = statisticsService.getOne(new QueryWrapper<>());
        model.addAttribute("statistics",statistics);
        return "outBreakDisplay";
    }
//    @GetMapping("/{id}")
//    public String chinaEpidemic(Model model,@PathVariable("id")String id) {
//        if (Integer.parseInt(id) == 2){
//            List<ChinaData> list = baseEpidemic2.list();
//            model.addAttribute("datalist",list);
//        }else {
//            List<ChinaData> list = baseEpidemic.list();
//            model.addAttribute("datalist",list);
//        }
//        return "outBreakDisplay";
//    }
    //全国现有趋势折线图
//    @GetMapping("/Curve")
//    public String curveData(Model model){
//        List<Curve> curveList = curve.list();
//        //将list集合解析出来 分为data 和nowConfirm
//        List<String> data4 = new ArrayList<>();
//        List<Integer> nowConfirm  = new ArrayList<>();
//        for (Curve curve : curveList){
//            data4.add(curve.getData());
//            nowConfirm.add(curve.getNowConfirm());
//        }
//        model.addAttribute("data4",new Gson().toJson(data4));
//        model.addAttribute("nowConfirm",new Gson().toJson(nowConfirm));
//        return "curve";
//    }
    //全国疫情新增趋势折线图
//    @GetMapping("/Polyline")
//    public String polylineData(Model model){
//        List<ChinaDayList> chinaDayLists = service.list();
//        //将list拆分成确证人数和疑似确证
//        List<Integer> confirm = new ArrayList<>();
//        List<Integer> suspect = new ArrayList<>();
//        List<String> data = new ArrayList<>();
//        for (ChinaDayList dayList : chinaDayLists){
//            confirm.add(dayList.getConfirm());
//            suspect.add(dayList.getSuspect());
//            data.add(dayList.getData());
//        }
//        model.addAttribute("confirm",new Gson().toJson(confirm));
//        model.addAttribute("suspect",new Gson().toJson(suspect));
//        model.addAttribute("data",new Gson().toJson(data));
//        return "polyline";
//    }
    //全国累计确证/治愈/死亡趋势折线图
//    @GetMapping("/Trend")
//    public String trendData(Model model){
//        List<Trend> list = trendService.list();
//        //将数据解析出来
//        List<Integer> confirm2 = new ArrayList<>();
//        List<Integer> heal = new ArrayList<>();
//        List<Integer> dead = new ArrayList<>();
//        List<String> data5 = new ArrayList<>();
//        for (Trend trend : list){
//            confirm2.add(trend.getConfirm());
//            heal.add(trend.getHeal());
//            dead.add(trend.getDead());
//            data5.add(trend.getData());
//        }
//        model.addAttribute("confirm2",new Gson().toJson(confirm2));
//        model.addAttribute("heal",new Gson().toJson(heal));
//        model.addAttribute("dead",new Gson().toJson(dead));
//        model.addAttribute("data5",new Gson().toJson(data5));
//        return "trend";
//    }
    //城外输入省事top10
//    @GetMapping("/Overseas")
//    public String overseasData(Model model){
//        List<Overseas> list = overseas.list();
//        Collections.sort(list);
//        List<String> area = new ArrayList<>();
//        List<Integer> tip = new ArrayList<>();
//
//        for (int i= 0;i<10;i++){
//            area.add(list.get(i).getArea());
//
//            tip.add(list.get(i).getTip());
//        }
//        model.addAttribute("area",new Gson().toJson(area));
//        model.addAttribute("tip",new Gson().toJson(tip));
//        return "overseas";
//    }
    //境外输入累计趋势折线图
//    @GetMapping("/Put")
//    public String inputData(Model model){
//        List<Input> inputList = input.list();
//        //将日期和人数拆分出来
//        List<String> data3 = new ArrayList<>();
//        List<Integer> importedCase2 = new ArrayList<>();
//        for (Input i : inputList){
//            data3.add(i.getData());
//            importedCase2.add(i.getImportedCase());
//        }
//        model.addAttribute("data3",new Gson().toJson(data3));
//        model.addAttribute("importedCase2",new Gson().toJson(importedCase2));
//        return "put";
//    }
    //境外输入新增趋势
//    @GetMapping("/AddInput")
//    public String addInput(Model model){
//        List<AddInput> addInputs = addInput.list();
//        //将日期和人数拆分出来
//        List<String> data2 = new ArrayList<>();
//        List<Integer> importedCase = new ArrayList<>();
//        for (AddInput put : addInputs){
//            data2.add(put.getData());
//            importedCase.add(put.getImportedCase());
//        }
//        model.addAttribute("data",new Gson().toJson(data2));
//        model.addAttribute("importedCase",new Gson().toJson(importedCase));
//        return "addInput";
//    }
    //全国现有确证构成:
//    @GetMapping("/Existing")
//    public String existingData(Model model){
//        List<Existing> existings = existing.list();
//        //将数据拆分 并添加总数
//        int count = 0;
//        for (Existing existing : existings){
//            count += existing.getValue();
//        }
//        model.addAttribute("count",new Gson().toJson(count));
//        model.addAttribute("existings",new Gson().toJson(existings));
//        return "existing";
//    }
}
