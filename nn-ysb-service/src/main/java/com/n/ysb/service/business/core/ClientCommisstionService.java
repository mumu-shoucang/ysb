package com.n.ysb.service.business.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;
import com.n.ysb.service.statistics.mapper.NnStatisticsMapper;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.po.NnStatisticsExample;
import com.n.ysb.service.statistics.po.NnStatisticsExample.Criteria;

@Service
public class ClientCommisstionService {
    
    @Autowired
    private NnStatisticsMapper statisticsMapper;
    
    @Autowired
    private NnReferrerMapper referrerMapper;
    
    public Map<String, Object> getCommisstionByRefSign(String yeeCustomerNumber, int pageNo, int limit){
        String refSign = this.getRefSignByYeeCustomerNumber(yeeCustomerNumber);
        
        Page<NnStatistics> startPage = PageHelper.startPage(pageNo, limit);
        NnStatisticsExample example = new NnStatisticsExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andStatisticsTypeEqualTo(refSign);
        
        example.setOrderByClause("STATISTICS_DATE desc");
        
        List<NnStatistics> selectByExample = statisticsMapper.selectByExample(example);
        
        return ReturnMap.suc(startPage.getResult());
    }
    
    private String getRefSignByYeeCustomerNumber(String yeeCustomerNumber){
        NnReferrerExample example = new NnReferrerExample();
        com.n.ysb.service.referrer.po.NnReferrerExample.Criteria criteria = example.createCriteria();
        criteria.andYeeCustomerNumberEqualTo(yeeCustomerNumber);
        
        List<NnReferrer> referrerList = referrerMapper.selectByExample(example);
        if(referrerList!=null && referrerList.size() == 1){
            return referrerList.get(0).getRefSign();
        }
        return "";
    }
}
