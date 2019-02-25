package com.n.ysb.service.withdraw.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.WithdrawStatus;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample.Criteria;
import com.n.ysb.service.withdraw.vo.NnYeecustomerWithdrawVo;

@Service
public class WithdrawService {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private NnYeecustomerWithdrawMapper withdrawMapper;
    
    public PageInfo<NnYeecustomerWithdraw> getOrderPage(NnYeecustomerWithdrawVo vo, int pageNo, int limit) {
        Page<NnYeecustomerWithdraw> startPage = PageHelper.startPage(pageNo, limit);
        
        NnYeecustomerWithdrawExample example = new NnYeecustomerWithdrawExample();
        Criteria criteria = example.createCriteria();
        
        if(StringUtils.isNotBlank(vo.getOrderNo())){
            criteria.andOrderNoEqualTo(vo.getOrderNo());
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        if(vo.getStartDate()!=null){
            Date startDate = vo.getStartDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format2.parse(format.format(startDate)+" 00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andCreateDateGreaterThanOrEqualTo(parse);
        }
        
        if(vo.getEndDate()!=null){
            Date endDate = vo.getEndDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format2.parse(format.format(endDate)+" 23:59:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andCreateDateLessThanOrEqualTo(parse);
        }
        
        if(StringUtils.isNotBlank(vo.getWithdrawStatus())){
            criteria.andWithdrawStatusEqualTo(vo.getWithdrawStatus());
        }
        
        if(vo.getWithdrawAmt()!=null){
            criteria.andWithdrawAmtEqualTo(vo.getWithdrawAmt());
        }
        
        example.setOrderByClause("CREATE_DATE　DESC");
        
        List<NnYeecustomerWithdraw> selectByExample = withdrawMapper.selectByExample(example);
        
        return startPage.toPageInfo();
    }
    
    public NnYeecustomerWithdraw getWithdrawOrderByOid(String oid){
        NnYeecustomerWithdrawExample example = new NnYeecustomerWithdrawExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(oid);
        
        List<NnYeecustomerWithdraw> withdrawOrderList = withdrawMapper.selectByExample(example);
        
        return withdrawOrderList != null && withdrawOrderList.size() == 1 ? withdrawOrderList.get(0) : null;
    }

    public List<NnYeecustomerWithdraw> getUnWithDrawSuccessOrderVo() {
        NnYeecustomerWithdrawExample example = new NnYeecustomerWithdrawExample();
        Criteria criteria = example.createCriteria();
        criteria.andWithdrawStatusEqualTo(WithdrawStatus.init.getCode());
        criteria.andYeeWithdrawStatusIsNull();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
        Date before10MiDate = calendar.getTime();
        
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 110);
        Date before120MiDate = calendar.getTime();
        
        criteria.andCreateDateGreaterThanOrEqualTo(before120MiDate);
        criteria.andCreateDateLessThanOrEqualTo(before10MiDate);
        
        List<NnYeecustomerWithdraw> WithdrawList = withdrawMapper.selectByExample(example);
        
        
        return WithdrawList;
    }
    
    public boolean updateWithdrawOrder(NnYeecustomerWithdraw withdraw){
        NnYeecustomerWithdrawExample example = new NnYeecustomerWithdrawExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(withdraw.getId());
        criteria.andWithdrawStatusNotEqualTo(withdraw.getWithdrawStatus());
        
        int c = withdrawMapper.updateByExampleSelective(withdraw, example);
        
        return c==1 ? true : false;
        
    }
}
