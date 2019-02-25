package com.n.ysb.service.merchant.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.merchant.FeeSetFlag;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;
import com.n.ysb.service.merchant.po.NnMerchantExample.Criteria;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.merchantCreditCard.mapper.NnMerchantCreditCardMapper;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCard;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample;
import com.n.ysb.service.thirdparty.ProductType;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class MerchantServiceImpl implements IMerchantService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private NnMerchantMapper merchantMapper;
	
	@Autowired
	private IYeePayRequestService yeePayRequestService;
	
	@Autowired
	private NnMerchantCreditCardMapper merchantCreditCardMapper;
	
    
    @Override
    public void updateMerchantFeeInfo(NnMerchantVo merchantVo){
        NnMerchant merchant = new NnMerchant();
        BeanUtils.copyProperties(merchantVo, merchant);
        NnMerchantExample example = new NnMerchantExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginMobileEqualTo(merchant.getLoginMobile());
        merchantMapper.updateByExampleSelective(merchant, example);
    }
    
    @Override
    public NnMerchantVo getMerchantByLoginMobile(String loginMobile){
        NnMerchantExample example = new NnMerchantExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginMobileEqualTo(loginMobile);
        List<NnMerchant> merchantList = merchantMapper.selectByExample(example);
        NnMerchant merchant = merchantList.size()>0 ? merchantList.get(0) : null;
        NnMerchantVo merchantVo = new NnMerchantVo();
        BeanUtils.copyProperties(merchant, merchantVo);
        
        return merchantVo;
    }
    
    @Override
	public PageInfo<NnMerchant> getMerchantPage(NnMerchantVo vo, int pageNo,
			int limit) {
		Page<NnMerchant> startPage = PageHelper.startPage(pageNo, limit);
    	NnMerchantExample example = new NnMerchantExample();
    	Criteria criteria = example.createCriteria();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
    	
    	// 商户编号
    	if(StringUtils.isNotBlank(vo.getMerchantCode())){
			criteria.andRefSignEqualTo(vo.getMerchantCode());
		}
    	// 手机号
    	if(StringUtils.isNotBlank(vo.getLoginMobile())){
    		criteria.andLoginMobileEqualTo(vo.getLoginMobile());
    	}
		// 注册时间	
		try {
			if(StringUtils.isNotBlank(vo.getStartTime())){
				startTime = sdf.parse(vo.getStartTime()+" 00:00:00");
				criteria.andCreateDateGreaterThanOrEqualTo(startTime);		
			}
			if(StringUtils.isNotBlank(vo.getEndTime())){
				endTime = sdf.parse(vo.getEndTime()+" 23:59:59");
				criteria.andCreateDateLessThanOrEqualTo(endTime);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		example.setOrderByClause("CREATE_DATE desc");
		
		List<NnMerchant> selectByExample = merchantMapper.selectByExample(example);
		
		log.info("===================商户信息分页查询:" + selectByExample+"=========================");
		return startPage.toPageInfo();
	}
    
    @Override
	public Map<String, Object> updateFeeSet(NnMerchantVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		boolean isSuc =false;
		Map<String, Object> tradeFeeSet = null;
		Map<String, Object> t0WithdrawFeeSet = null;
		Map<String, Object> t0WithdrawWorkdayFeeSet = null;
		Map<String, Object> t0WithdrawNonworkdayFeeSet = null;
		NnMerchant merchant = merchantMapper.selectByPrimaryKey(vo.getId());
		BeanUtils.copyProperties(merchant, vo);
		if(merchant.getFeeSetType()==null||merchant.getFeeSetType().equals("")){
			tradeFeeSet = yeePayRequestService.tradeFeeSet(vo);
			t0WithdrawFeeSet = yeePayRequestService.t0WithdrawFeeSet(vo);
			t0WithdrawWorkdayFeeSet = yeePayRequestService.t0WithdrawWorkdayFeeSet(vo);
			t0WithdrawNonworkdayFeeSet = yeePayRequestService.t0WithdrawNonworkdayFeeSet(vo);
			log.info("费率设置-:{}",tradeFeeSet+"=========="+t0WithdrawFeeSet+"==============="+t0WithdrawWorkdayFeeSet+"==========="+t0WithdrawNonworkdayFeeSet);
			if(!tradeFeeSet.get("message").equals("成功")||
			   !t0WithdrawFeeSet.get("message").equals("成功")||
			   !t0WithdrawWorkdayFeeSet.get("message").equals("成功")||
			   !t0WithdrawNonworkdayFeeSet.get("message").equals("成功")){
				
				String tradeFee = "";
				String t0WithdrawFee = "";
				String t0WinthdrawWorkdayFee = "";
				String t0WithdrawNonworkdayFfee = "";
				
				if(tradeFeeSet.get("message").equals("成功")){
					tradeFee = ProductType.trade_fee.getCode();
				}if(t0WithdrawFeeSet.get("message").equals("成功")){
					t0WithdrawFee = ProductType.t0_withdraw_fee.getCode();
				}if(t0WithdrawWorkdayFeeSet.get("message").equals("成功")){
					t0WinthdrawWorkdayFee = ProductType.t0_withdraw_workday_fee.getCode();
				}if(t0WithdrawNonworkdayFeeSet.get("message").equals("成功")){
					t0WithdrawNonworkdayFfee = ProductType.t0_withdraw_nonworkday_fee.getCode();
				}
				NnMerchant po = new NnMerchant();
				po.setId(vo.getId());
				po.setFeeSetType(tradeFee+t0WithdrawFee+t0WinthdrawWorkdayFee+t0WithdrawNonworkdayFfee);
				po.setFeeSetFlag(FeeSetFlag.FEE_SET_FAIL.getCode());
				int updateByPrimaryKeySelective = merchantMapper.updateByPrimaryKeySelective(po);
				isSuc = updateByPrimaryKeySelective>0 ? true : false;
				log.info("====================修改费率失败-:{}:"+isSuc);
				if(updateByPrimaryKeySelective>0){
					map.put("fail", isSuc);
				}else{
					map.put("abnormal", isSuc);
				}
			}else{
				NnMerchant po = new NnMerchant();
				po.setId(vo.getId());
				po.setFeeSetType(ProductType.trade_fee.getCode()+ProductType.t0_withdraw_fee.getCode()+
						ProductType.t0_withdraw_workday_fee.getCode()
						+ProductType.t0_withdraw_nonworkday_fee.getCode());
				po.setFeeSetFlag(FeeSetFlag.FEE_SET_SUCCESS.getCode());
				int updateByPrimaryKeySelective = merchantMapper.updateByPrimaryKeySelective(po);
				isSuc = updateByPrimaryKeySelective>0 ? true : false;
				log.info("====================修改费率成功:"+isSuc);
				if(updateByPrimaryKeySelective>0){
					map.put("isSuc", isSuc);
				}else{
					map.put("abnormal", isSuc);
				}
			}
		}else{
			boolean tradeFeeNo = true;
			boolean t0WithdrawFeeNo = true;
			boolean t0WinthdrawWorkdayFeeNo = true;
			boolean t0WithdrawNonworkdayFfeeNo = true;
			
			String tradeFee = "";
			String t0WithdrawFee = "";
			String t0WinthdrawWorkdayFee = "";
			String t0WithdrawNonworkdayFfee = "";
			
			char[] feeSetTypes = merchant.getFeeSetType().toCharArray();
			for (char c : feeSetTypes) {
				String nameByCode = ProductType.getNameByCode(c+"");				
				if(nameByCode.equals(ProductType.trade_fee.name())){
					tradeFeeNo = false;
				}if(nameByCode.equals(ProductType.t0_withdraw_fee.name())){
					t0WithdrawFeeNo = false;
				}if(nameByCode.equals(ProductType.t0_withdraw_workday_fee.name())){
					t0WinthdrawWorkdayFeeNo = false;
				}if(nameByCode.equals(ProductType.t0_withdraw_nonworkday_fee.name())){
					t0WithdrawNonworkdayFfeeNo = false;
				}			
			}
			if(tradeFeeNo){
				tradeFeeSet = yeePayRequestService.tradeFeeSet(vo);
				if(tradeFeeSet.get("message").equals("成功")){
					tradeFee = ProductType.trade_fee.getCode();
				}else{
					map.put("fail", isSuc);
					return map;
				}
			}if(t0WithdrawFeeNo){
				t0WithdrawFeeSet = yeePayRequestService.t0WithdrawFeeSet(vo);
				if(t0WithdrawFeeSet.get("message").equals("成功")){
					t0WithdrawFee = ProductType.t0_withdraw_fee.getCode();
				}else{
					map.put("fail", isSuc);
					return map;
				}
			}if(t0WinthdrawWorkdayFeeNo){
				t0WithdrawWorkdayFeeSet = yeePayRequestService.t0WithdrawWorkdayFeeSet(vo);
				if(t0WithdrawWorkdayFeeSet.get("message").equals("成功")){
					t0WinthdrawWorkdayFee = ProductType.t0_withdraw_workday_fee.getCode();
				}else{
					map.put("fail", isSuc);
					return map;
				}
			}if(t0WithdrawNonworkdayFfeeNo){
				t0WithdrawNonworkdayFeeSet = yeePayRequestService.t0WithdrawNonworkdayFeeSet(vo);	
				if(t0WithdrawNonworkdayFeeSet.get("message").equals("成功")){
					t0WithdrawNonworkdayFfee = ProductType.t0_withdraw_nonworkday_fee.getCode();
				}else{
					map.put("fail", isSuc);
					return map;
				}
			}
			NnMerchant po = new NnMerchant();
			po.setId(vo.getId());
			po.setFeeSetType(vo.getFeeSetType()+tradeFee+t0WithdrawFee+t0WinthdrawWorkdayFee+t0WithdrawNonworkdayFfee);
			po.setFeeSetFlag(FeeSetFlag.FEE_SET_SUCCESS.getCode());
			int updateByPrimaryKeySelective = merchantMapper.updateByPrimaryKeySelective(po);
			isSuc = updateByPrimaryKeySelective>0 ? true : false;
			log.info("====================修改费率成功-:{}:"+isSuc);
			if(updateByPrimaryKeySelective>0){
				map.put("isSuc", isSuc);
			}else{
				map.put("abnormal", isSuc);
			}
		}
		
		
		return map;
	}
    
    @Override
    public Map<String,Object> getMerchantDetails(String id) {
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	// merchant
    	NnMerchant merchant = merchantMapper.selectByPrimaryKey(id);
    	
    	// creditCards
    	NnMerchantCreditCardExample condition = new NnMerchantCreditCardExample();
    	com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample.Criteria criteria = condition.createCriteria();
    	if(StringUtils.isNotBlank(merchant.getLoginMobile())){
			criteria.andMerchantMobileEqualTo(merchant.getLoginMobile());
		}
    	List<NnMerchantCreditCard> creditCards = merchantCreditCardMapper.selectByExample(condition);
    	log.info("====================查询商户详情-:{}:"+creditCards);
    	
    	map.put("merchant", merchant);
    	map.put("merchantCreditCards", creditCards);
    	return map;
    }
    
    @Override
    public List<NnMerchantVo> getAllMerchant(){
        List<NnMerchantVo> returnMerchantList = new ArrayList<NnMerchantVo>();
        
        NnMerchantExample example = new NnMerchantExample();
        List<NnMerchant> merchantList = merchantMapper.selectByExample(example);
        
        for (NnMerchant merchant : merchantList) {
            NnMerchantVo merchantVo = new NnMerchantVo();
            BeanUtils.copyProperties(merchant, merchantVo);
            returnMerchantList.add(merchantVo);
        }
        
        return returnMerchantList;
    }
    
    @Override
    public List<NnMerchantVo> getMerchantByRefSign(String refSign){
        List<NnMerchantVo> returnMerchantList = new ArrayList<NnMerchantVo>();
        
        NnMerchantExample example = new NnMerchantExample();
        
        Criteria criteria = example.createCriteria();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timsStr = format.format(time);
        
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = format2.parse(timsStr+ " 00:00:00");
            endTime = format2.parse(timsStr+ " 23:59:59");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        criteria.andCreateDateGreaterThanOrEqualTo(startTime);
        criteria.andCreateDateLessThanOrEqualTo(endTime);
        criteria.andRefSignEqualTo(refSign);
        
        Criteria criteria2 = example.or();
        criteria2.andAuthDateGreaterThanOrEqualTo(startTime);
        criteria2.andAuthDateLessThanOrEqualTo(endTime);
        criteria2.andRefSignEqualTo(refSign);
        
        List<NnMerchant> merchantList = merchantMapper.selectByExample(example);
        
        for (NnMerchant merchant : merchantList) {
            NnMerchantVo merchantVo = new NnMerchantVo();
            BeanUtils.copyProperties(merchant, merchantVo);
            returnMerchantList.add(merchantVo);
        }
        
        return returnMerchantList;
    }
    
    @Override
    public NnMerchant getYeeCustomerNumber(String idCard) {
    		NnMerchantExample condition = new NnMerchantExample();
        Criteria criteria = condition.createCriteria();
        criteria.andIdCardEqualTo(idCard);
        
    		List<NnMerchant> merchantList = merchantMapper.selectByExample(condition);
    		if(merchantList != null && merchantList.size() == 1) {
    			return merchantList.get(0);
    		}
    		
    		return new NnMerchant();
    }
    
    @Override
    public NnMerchant getMerchantByYeecustomerNumber(String yeeCustomerNumber) {
        NnMerchantExample condition = new NnMerchantExample();
        Criteria criteria = condition.createCriteria();
        criteria.andYeeCustomerNumberEqualTo(yeeCustomerNumber);

        List<NnMerchant> merchantList = merchantMapper.selectByExample(condition);
        
        return merchantList != null && merchantList.size() == 1 ? merchantList.get(0) : null;
    }
    
}
