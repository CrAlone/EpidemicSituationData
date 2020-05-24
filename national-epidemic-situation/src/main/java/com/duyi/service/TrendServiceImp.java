package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Trend;
import com.duyi.mapper.TrendMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class TrendServiceImp extends ServiceImpl<TrendMapper, Trend> implements BaseTrendService{
}
