package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Overseas;
import com.duyi.mapper.OverseasMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class OverseasServiceImp extends ServiceImpl<OverseasMapper,Overseas> implements BaseOverseas{
}
