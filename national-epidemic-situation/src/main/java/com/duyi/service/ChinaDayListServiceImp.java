package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.ChinaDayList;

import com.duyi.mapper.ChinaDayListMapper;

import org.springframework.stereotype.Service;

/**
 * @author cr
 */
@Service
public class ChinaDayListServiceImp extends ServiceImpl<ChinaDayListMapper, ChinaDayList> implements BaseChinaDayListService{
}
