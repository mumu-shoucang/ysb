package com.n.ysb.service.business.core;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.thirdparty.WithdrawType;
import com.n.ysb.service.thirdparty.vo.BalanceType;
import com.n.ysb.service.thirdparty.vo.CustomerInforUpdateVo;
import com.n.ysb.service.thirdparty.vo.ReceiveVo;
import com.n.ysb.service.thirdparty.vo.RegisterVo;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class YeepayBusService {

	@Autowired
	private IYeePayRequestService yeePayRequestService;
	
	
	public Thread FeesetProcessor(final NnMerchant merchant) {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				NnMerchantVo par = new NnMerchantVo();
				par.setYeeCustomerNumber(merchant.getYeeCustomerNumber());
				par.setLoginMobile(merchant.getLoginMobile());
				yeePayRequestService.feeSet(par);
			}
		});
	} 
	
	
	public YeeReturn receive(NnMerchant merchant, NnOrder order) {

		NnMerchantVo merchantVo = new NnMerchantVo();
		merchantVo.setYeeCustomerNumber(merchant.getYeeCustomerNumber());
		
		Map<String, Object> ret = yeePayRequestService.receive(merchantVo, new ReceiveVo(order));
		
		String code = ret.get("code").toString();
		String message = ret.get("message").toString();
		
		if("0000".equals(code)) {
			String url = ret.get("url").toString();
			return new YeeReturn(true, "", url);
		}
		
		return new YeeReturn(false, message, "");
	}
	
	public YeeReturn updateCustomerBankInfo(NnMerchant merchant) {
		
		CustomerInforUpdateVo pars = new CustomerInforUpdateVo();
		pars.setModifyType("2");
		pars.setYeeCustomerNumber(merchant.getYeeCustomerNumber());
		pars.setBankAccountNo(merchant.getBankAccountNo());
		pars.setBankName(merchant.getBankName());
            
		Map<String, Object> ret = yeePayRequestService.customerInforUpdate(pars);
		String code = ret.get("code").toString();
		String message = ret.get("message").toString();
		
		if("0000".equals(code)) {
			return new YeeReturn(true, "", "");
		}
		
		return new YeeReturn(false, message, "");
	}
	
	public YeeReturn register(NnMerchant merchant, File bankCardPhoto, File idCardPhoto, File idCardBackPhoto, File personPhoto) {
		
		String bankAccountNo = merchant.getBankAccountNo();
		String bankAccountName = merchant.getBankAccountName();
		String bankName = merchant.getBankName();
		String idCard = merchant.getIdCard();
		String bindMobile = merchant.getBindMobile();
		String merchantCode = merchant.getMerchantCode();
		
		RegisterVo par = new RegisterVo();
		par.setIdCard(idCard);
		
		par.setBankAccountNo(bankAccountNo);
		par.setBankAccountName(bankAccountName);
		par.setLegalPerson(bankAccountName);
		par.setBankName(bankName);
		par.setSignedName(bankAccountName);
		
		
//		par.setMinSettleAmount("50");
//		par.setLinkMan("HLXC");
		par.setBindMobile(bindMobile);
		par.setRequestId(merchantCode);
		
		par.setBankCardPhoto(bankCardPhoto);
		par.setIdCardPhoto(idCardPhoto);
		par.setIdCardBackPhoto(idCardBackPhoto);
		par.setPersonPhoto(personPhoto);
		
		Map<String, Object> ret = yeePayRequestService.register(par);
		String code = ret.get("code").toString();
		String message = ret.get("message").toString();
		
		if("0000".equals(code)) {
			String customerNumber = ret.get("customerNumber").toString();
			return new YeeReturn(true, "", customerNumber);
		}
		
		return new YeeReturn(false, message, "");
	}
	
	public YeeReturn balanceQuery(String yeeCustomerNumber, String balanceType) {
	    Map<String, Object> ret = yeePayRequestService.balanceQuery(yeeCustomerNumber, BalanceType.getValueByCode(balanceType));
	    String code = ret.get("code").toString();
        String message = ret.get("message").toString();
	    
        if("0000".equals(code)) {
            String balance = ret.get("balance").toString();
            return new YeeReturn(true, "", balance);
        }
        
        return new YeeReturn(false, message, "");
    }
	
	public YeeReturn withdraw(NnMerchantVo merchantVo, WithDrawVo withDrawVo){
	    Map<String, Object> ret = yeePayRequestService.withDraw(merchantVo, withDrawVo, WithdrawType.merchant_withdraw.getCode());
	    String code = ret.get("code").toString();
        String message = ret.get("message").toString();
	    
        if("0000".equals(code)) {
            return new YeeReturn(true, "", "");
        }
        return new YeeReturn(false, message, "");
	}
	
	public class YeeReturn {
		
		private boolean isSuc;
		private String desc;
		private String body;
		
		public YeeReturn(boolean isSuc, String desc, String body) {
			this.isSuc = isSuc;
			this.desc = desc;
			this.body = body;
		}
		
		public boolean isSuc() {
			return isSuc;
		}
		public void setSuc(boolean isSuc) {
			this.isSuc = isSuc;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
	}
}
