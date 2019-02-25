package com.n.ysb.service.business.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.MCC;
import com.n.ysb.service.business.StatPerMobile;
import com.n.ysb.service.business.pars.ReceivePars;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.po.NnGlobalCfg;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;

@Service
public class RecieveValidator {
	
	@Autowired
	private BusRedis busRedis;
	@Autowired
	private WorkDayService workDayService;
	@Autowired
	private IGlobalCfgService globalCfgService;
	
	/**
	 * 业务校验（）
	 */
	public ValidateCode validate(ReceivePars pars) {
		
		if(!MCC.contains(pars.getMcc())) {
			return new ValidateCode(false, "商品分类有误"); 
		}
		
		NnGlobalCfg cfg = getCfg();
		Long dayCount = cfg.getDayCount();
		Long dayLimit = cfg.getDayLimit();
		Long monthCount = cfg.getMonthCount();
		Long monthLimit = cfg.getMonthLimit();
		Long singleLimit = cfg.getSingleLimit();
		
		Long orderAmtL = Long.valueOf(pars.getOrderAmt().setScale(0, RoundingMode.HALF_UP).toString()); 
		// singl limit
		if(pars.getOrderAmt().compareTo(new BigDecimal(singleLimit)) == 1) {
			return new ValidateCode(false, "单笔额度超限"); 
		}
		StatPerMobile stat = busRedis.getStat(pars.getMerchantMobile().toString());
		// day count
		if(stat.getDayCount() + 1 > dayCount) {
			return new ValidateCode(false, "日累积交易次数超限"); 
		}
		// day limit
		if(stat.getDayLimit() + orderAmtL  > dayLimit) {
			return new ValidateCode(false, "日累积交易额度超限"); 
		}
		// month count
		if(stat.getMonthCount() + 1 > monthCount) {
			return new ValidateCode(false, "月累积交易次数超限"); 
		}
		// month limit
		if(stat.getMonthLimit() + orderAmtL > monthLimit) {
			return new ValidateCode(false, "月累积交易额度超限"); 
		}
		
		// 1:交易费率 2:T1 自助结算费率 3:T0 自助结算基本费率 4:T0 自助结算工作日额外费 率 5:T0 自助结算非工作日额外 费率
		FeeVo fee = computePayWithdrawFee(pars.getOrderAmt(), cfg);
		pars.setTradeFee(fee.getTradeFee());
		pars.setT0WithdrawFee(fee.getT0WithdrawFee());
		pars.setT0WithdrawEXFee(fee.getT0WithdrawEXFee());

		return ValidateCode.suc();
	}
	
	public Map<String, Object> computeFee(BigDecimal orderAmt) {
		NnGlobalCfg cfg = getCfg();
		
		FeeVo fee = computePayWithdrawFee(orderAmt, cfg);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("tradeFee", fee.getTradeFee());
		ret.put("T0WithdrawFee", fee.getT0WithdrawFee());
		ret.put("T0WithdrawEXFee", fee.getT0WithdrawEXFee());
		return ret;
	}
	
	private FeeVo computePayWithdrawFee(BigDecimal orderAmt, NnGlobalCfg cfg) {
		BigDecimal T0WithdrawFee = cfg.getT0WithdrawFee(); // 元
		BigDecimal tradeFee = cfg.getTradeFee().divide(new BigDecimal(100));
		BigDecimal T0WithdrawNonworkdayFee = cfg.getT0WithdrawNonworkdayFee().divide(new BigDecimal(100));
		BigDecimal T0WithdrawWorkdayFee = cfg.getT0WithdrawWorkdayFee().divide(new BigDecimal(100));
		// 可结算金额 = 订单金额 - 订单金额*收款手续费
		BigDecimal tradeFeeAmt = orderAmt.multiply(tradeFee).setScale(2, RoundingMode.HALF_UP);
		BigDecimal canWithdrawAmt = orderAmt.subtract(tradeFeeAmt).setScale(2, RoundingMode.HALF_UP);
		boolean isHoliday = workDayService.isHoliday(new Date());
		BigDecimal T0WithdrawEXFee = canWithdrawAmt.multiply(isHoliday ? T0WithdrawNonworkdayFee : T0WithdrawWorkdayFee).setScale(2, RoundingMode.HALF_UP);
		
		FeeVo fee = new FeeVo();
		fee.setTradeFee(tradeFeeAmt);
		fee.setT0WithdrawFee(T0WithdrawFee);
		fee.setT0WithdrawEXFee(T0WithdrawEXFee);
		return fee;
	}
	
	private class FeeVo {
		BigDecimal tradeFee;
		BigDecimal T0WithdrawFee;
		BigDecimal T0WithdrawEXFee;
		
		public BigDecimal getTradeFee() {
			return tradeFee;
		}
		public void setTradeFee(BigDecimal tradeFee) {
			this.tradeFee = tradeFee;
		}
		public BigDecimal getT0WithdrawFee() {
			return T0WithdrawFee;
		}
		public void setT0WithdrawFee(BigDecimal t0WithdrawFee) {
			T0WithdrawFee = t0WithdrawFee;
		}
		public BigDecimal getT0WithdrawEXFee() {
			return T0WithdrawEXFee;
		}
		public void setT0WithdrawEXFee(BigDecimal t0WithdrawEXFee) {
			T0WithdrawEXFee = t0WithdrawEXFee;
		}
	}
	
	public NnGlobalCfg getCfg() {
		NnGlobalCfgVo vo = globalCfgService.getRedisGlobalCfgVo();
		if(vo == null) {
			return null;
		}
		NnGlobalCfg po = new NnGlobalCfg();
		BeanUtils.copyProperties(vo, po);
		return po;
	}
	
	static class ValidateCode {

		private boolean isSuc;
		private String code;
		private String desc;
		
		public ValidateCode(boolean isSuc, String desc) {
			this.isSuc = isSuc;
			this.code = isSuc ? "" : ReturnCode.bus_invalid.getCode();
			this.desc = isSuc ? "" : desc;
		}
		
		public static ValidateCode suc() {
			return new ValidateCode(true, "校验通过");
		}
		public static ValidateCode fail(String desc) {
			return new ValidateCode(false, desc);
		}
		
		public boolean isSuc() {
			return isSuc;
		}
		public void setSuc(boolean isSuc) {
			this.isSuc = isSuc;
		}

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
