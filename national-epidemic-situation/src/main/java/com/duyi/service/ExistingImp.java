package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Existing;
import com.duyi.mapper.ExistingMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class ExistingImp extends ServiceImpl<ExistingMapper, Existing> implements BaseExisting{
}
