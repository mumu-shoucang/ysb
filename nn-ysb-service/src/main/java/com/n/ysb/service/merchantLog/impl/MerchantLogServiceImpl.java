package com.n.ysb.service.merchantLog.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.merchantLog.IMerchantLogService;
import com.n.ysb.service.merchantLog.mapper.NnMerchantLogMapper;
import com.n.ysb.service.merchantLog.po.NnMerchantLog;
import com.n.ysb.service.merchantLog.vo.NnMerchantLogVo;

@Service
public class MerchantLogServiceImpl implements IMerchantLogService {

    @Autowired
    private NnMerchantLogMapper merchantLogMapper;
    
    @Override
    public void addMerchantLog(NnMerchantLogVo merchantLogVo){
        NnMerchantLog merchantLog = new NnMerchantLog();
        BeanUtils.copyProperties(merchantLogVo, merchantLog);
        merchantLogMapper.insertSelective(merchantLog);
    }
}
