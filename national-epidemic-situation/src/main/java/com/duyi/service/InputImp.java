package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Input;
import com.duyi.mapper.InputMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class InputImp extends ServiceImpl<InputMapper, Input> implements BaseInput{
}
