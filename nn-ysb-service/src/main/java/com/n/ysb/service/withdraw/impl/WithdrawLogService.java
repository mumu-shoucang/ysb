package com.n.ysb.service.withdraw.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawLogMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLogExample;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLogExample.Criteria;
import com.n.ysb.service.withdraw.vo.NnYeecustomerWithdrawLogVo;

@Service
public class WithdrawLogService {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private NnYeecustomerWithdrawLogMapper withdrawLogMapper;
    
    
    public List<NnYeecustomerWithdrawLog> getWithdrawLog(String orderNo){
        NnYeecustomerWithdrawLogExample example = new NnYeecustomerWithdrawLogExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andOrderNoEqualTo(orderNo);
        List<NnYeecustomerWithdrawLog> withdrawLogList = withdrawLogMapper.selectByExample(example);
        
        return withdrawLogList;
        
    }
}
