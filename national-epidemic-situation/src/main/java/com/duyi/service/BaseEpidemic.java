package com.duyi.service;

import com.duyi.bean.ChinaData;

import java.util.List;

/**
 * @author cr
 */
public interface BaseEpidemic {
    /**
     *
     * @return 通过方法获取中国地区的所有疫情数据
     *
     */
    List<ChinaData> list() ;
}

