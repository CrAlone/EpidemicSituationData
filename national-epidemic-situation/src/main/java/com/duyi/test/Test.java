package com.duyi.test;

import com.duyi.bean.Trend;
import com.duyi.handler.TrendData;

import java.util.List;

/**
 * @author cr
 */
public class Test {
    public static void main(String[] args) {
        List<Trend> list = TrendData.getData();
        System.out.println(list);
    }
}
