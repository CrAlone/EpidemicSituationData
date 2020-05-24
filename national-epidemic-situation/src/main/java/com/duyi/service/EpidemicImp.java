package com.duyi.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duyi.bean.ChinaData;
import com.duyi.mapper.Mapper;
//import com.duyi.handler.DataConversion;
//import com.duyi.handler.JsoupHandler;
import org.springframework.stereotype.Service;

//import java.util.List;

/**
 * @author cr
 */
@Service
public class EpidemicImp extends ServiceImpl<Mapper,ChinaData> implements BaseEpidemic {
//    @Override
//    public List<ChinaData> list() {
//        try {
//            return DataConversion.read();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<ChinaData> dataList(int id) {
//        if (id == 2){
//            try {
//                return JsoupHandler.doGit();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return list();
//    }
}
