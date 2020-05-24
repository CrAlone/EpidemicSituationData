package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.AddInput;
import com.duyi.mapper.AddInputMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class AddInputImp extends ServiceImpl<AddInputMapper, AddInput> implements BaseAddInput{
}
