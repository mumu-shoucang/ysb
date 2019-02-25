package com.n.ysb.service.business.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.core.YeepayBusService.YeeReturn;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;

@Service
public class ClientBalanceService {
    @Autowired
    private YeepayBusService yeepayBusService;

    public Map<String, Object> balanceQuery(String yeeCustomerNumber, String balanceType) {
        YeeReturn yeeRet = yeepayBusService.balanceQuery(yeeCustomerNumber, balanceType);
        
        if(!yeeRet.isSuc()) {
            return ReturnMap.New(ReturnCode.bus_invalid.getCode(), yeeRet.getDesc());
        }
        
        return ReturnMap.queryBalanceSuc(yeeRet.getBody());
    }
    
    
}
