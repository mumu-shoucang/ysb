package com.n.ysb.service.business.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.pars.ForgetPWDPars;
import com.n.ysb.service.business.pars.LoginPars;
import com.n.ysb.service.business.pars.RegisterPars;
import com.n.ysb.service.business.pars.UpdatePWDPars;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.business.validator.parsAuthValid.AppKey;
import com.n.ysb.service.merchant.FeeSetFlag;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;
import com.n.ysb.service.merchant.po.NnMerchantExample.Criteria;
import com.n.ysb.service.referrer.impl.OnOffStatus;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;
import com.n.ysb.service.util.AESCipher;
import com.n.ysb.service.util.AESUtil;

@Service
public class RegLoginService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private BusRedis busRedis;
	@Autowired
	private NnMerchantMapper merchantDao;
	@Autowired
	private NnReferrerMapper referrerDao;
	
	@Autowired
    private AppKey appKey;
	
	public Map<String, Object> register(RegisterPars pars) {
		// check smsCode
		String mobile = pars.getMerchantMobile().toString();
		if(!pars.getSmsCode().equals(busRedis.getSmsCode(mobile))) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "短信验证码非法");
		}
		
		NnMerchant merchant = new NnMerchant();
		merchant.setLoginMobile(mobile);
		merchant.setLoginPwd(pars.getPwd());
		merchant.setCreateDate(new Date());
		merchant.setFeeSetFlag(FeeSetFlag.FEE_SET_NONE.getCode());
		
		NnReferrerExample condition = new NnReferrerExample();
		com.n.ysb.service.referrer.po.NnReferrerExample.Criteria criteria = condition.createCriteria();
		criteria.andRefCodeEqualTo(pars.getRefCode());
		criteria.andRefStatusEqualTo(OnOffStatus.enable.getKey());
		List<NnReferrer> referrers = referrerDao.selectByExample(condition);
		if(referrers == null || referrers.size() == 0 || referrers.size() > 1) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "邀请码非法");
		}
		merchant.setRefSign(referrers.get(0).getRefSign());
		try{
			int c = merchantDao.insertSelective(merchant);
			if(c != 1){
				return ReturnMap.fail();
			}
			return ReturnMap.suc();
		} catch(Exception ex) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "手机号码已注册");
		}
	}
	
	public Map<String, Object> login(LoginPars pars, String header) {
		String mobile = pars.getMerchantMobile().toString();
		String requestPwd = pars.getPwd();
		//密码解密
		if(header.contains("Android")){
		    requestPwd = AESUtil.decrypt(requestPwd, appKey.getAesKey());
		}else if(header.contains("iPhone") || header.contains("iPod") || header.contains("iPad")){
            requestPwd = urlDecode(requestPwd);
            requestPwd = AESCipher.aesDecryptString(requestPwd, appKey.getAesKey());
		}else{
		    //Android
		    requestPwd = AESUtil.decrypt(requestPwd, appKey.getAesKey());
		    
		    //ios
//            requestPwd = urlDecode(requestPwd);
//            requestPwd = AESCipher.aesDecryptString(requestPwd, appKey.getAesKey());
		}
		
		NnMerchantExample condition = new NnMerchantExample();
		Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(mobile);
		// check mobile
		List<NnMerchant> merchants = merchantDao.selectByExample(condition);
		if(merchants == null || merchants.size() == 0) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "手机号码未注册");
		}
		// check pwd
		NnMerchant merchant = merchants.get(0);
		
		String loginPwd = merchant.getLoginPwd();
		
		String androidPwd = AESUtil.decrypt(loginPwd, appKey.getAesKey());
		
        String iosPwd = AESCipher.aesDecryptString(urlDecode(loginPwd), appKey.getAesKey());
    
        if(!(requestPwd).equals(androidPwd) && !requestPwd.equals(iosPwd)) {
            return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "密码有误");
        }
		
		String token = IDGenerator.buildToken(mobile, requestPwd+"");
		// set redis token
		busRedis.setToken(mobile, token);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("token", token);
		return ReturnMap.suc(ret);
	}
	
	private String urlDecode(String text){
        try {
            String decodedText = URLDecoder.decode(text, "UTF-8");
            return decodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("密码{} decode Exception", text);
        return "";
    }
	
	public Map<String, Object> logout(LoginPars pars) {
		// do nothing
		String mobile = pars.getMerchantMobile().toString();
//		busRedis.delToken(mobile);
		return ReturnMap.suc();
	}
	
	public Map<String, Object> updatePWD(UpdatePWDPars pars) {
		String mobile = pars.getMerchantMobile().toString();
		NnMerchantExample condition = new NnMerchantExample();
		Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(mobile);
		NnMerchant merchant = new NnMerchant();
		merchant.setLoginPwd(pars.getNewPwd());
		int c = merchantDao.updateByExampleSelective(merchant, condition);
		if(c != 1){
			return ReturnMap.fail();
		}
		return ReturnMap.suc();
	}
	
	public Map<String, Object> forgetPWD(ForgetPWDPars pars) {
		// check smsCode
		String mobile = pars.getMerchantMobile().toString();
		if(!busRedis.getSmsCode(mobile).equals(pars.getSmsCode())) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "短信验证码非法");
		}
		
		NnMerchantExample condition = new NnMerchantExample();
		Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(mobile);
		NnMerchant merchant = new NnMerchant();
		merchant.setLoginPwd(pars.getNewPwd());
		try{
			int c = merchantDao.updateByExampleSelective(merchant, condition);
			if(c != 1) {
				return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "手机号码未注册");
			}
			return ReturnMap.suc();
		} catch(Exception ex) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "手机号码未注册");
		}
	}
	
}
