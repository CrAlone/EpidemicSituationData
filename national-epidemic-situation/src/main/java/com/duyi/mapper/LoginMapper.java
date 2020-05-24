package com.duyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duyi.bean.Person;
import org.springframework.stereotype.Repository;

/**
 * @author cr
 */
@Repository
public interface LoginMapper extends BaseMapper<Person> {
}
