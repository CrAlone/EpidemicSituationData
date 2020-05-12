package com.duyi.service;

import com.duyi.bean.ChinaData;
import com.duyi.util.DataConversion;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cr
 */
@Service
public class EpidemicImp implements BaseEpidemic {
    @Override
    public List<ChinaData> list() {
        try {
            return DataConversion.read();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
