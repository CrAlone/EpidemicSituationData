package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.Person;
import com.duyi.mapper.LoginMapper;
import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class PersonImp extends ServiceImpl<LoginMapper, Person> implements BasePerson{

}
