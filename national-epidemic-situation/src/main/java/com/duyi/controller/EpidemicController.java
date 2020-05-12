package com.duyi.controller;

import com.duyi.bean.ChinaData;
import com.duyi.service.BaseEpidemic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author cr
 */
@Controller
@RequestMapping("/Epidemic")
public class EpidemicController {
    @Autowired
    private BaseEpidemic baseEpidemic;
    @GetMapping
    public String chinaEpidemic(Model model) {
        List<ChinaData> list = baseEpidemic.list();
        model.addAttribute("datalist",list);
        return "outBreakDisplay";
    }
}
