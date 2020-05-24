package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Curve;
import com.duyi.mapper.CurveMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class CurveImp extends ServiceImpl<CurveMapper, Curve> implements BaseCurve{
}
