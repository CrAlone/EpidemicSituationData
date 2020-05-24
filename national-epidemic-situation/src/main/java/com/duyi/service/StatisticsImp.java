package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Statistics;
import com.duyi.mapper.StatisticsMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class StatisticsImp extends ServiceImpl<StatisticsMapper, Statistics> implements BaseStatisticsService{
}
