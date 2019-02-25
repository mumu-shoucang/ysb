package com.n.ysb.web.business;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.n.ysb.service.business.ParsBasicValidator;
import com.n.ysb.service.business.core.ClientBalanceService;
import com.n.ysb.service.business.core.ClientCommisstionService;
import com.n.ysb.service.business.core.ClientWithdrawService;
import com.n.ysb.service.business.core.MerchantInfoService;
import com.n.ysb.service.business.core.ReceiveService;
import com.n.ysb.service.business.core.RegLoginService;
import com.n.ysb.service.business.core.SMSService;
import com.n.ysb.service.business.pars.AddCreditCardPars;
import com.n.ysb.service.business.pars.CompleteMerchantPars;
import com.n.ysb.service.business.pars.ComputeFeePars;
import com.n.ysb.service.business.pars.ForgetPWDPars;
import com.n.ysb.service.business.pars.GetBalancePars;
import com.n.ysb.service.business.pars.GetCommisstionPars;
import com.n.ysb.service.business.pars.GetCreditCardPars;
import com.n.ysb.service.business.pars.GetFeePars;
import com.n.ysb.service.business.pars.GetMerchantInfoPars;
import com.n.ysb.service.business.pars.LoginPars;
import com.n.ysb.service.business.pars.QueryReceivePars;
import com.n.ysb.service.business.pars.QueryWithdrawPars;
import com.n.ysb.service.business.pars.ReceivePars;
import com.n.ysb.service.business.pars.RegisterPars;
import com.n.ysb.service.business.pars.RemoveCreditCardPars;
import com.n.ysb.service.business.pars.SendSMSCodePars;
import com.n.ysb.service.business.pars.UpdateMerchantBankAccountPars;
import com.n.ysb.service.business.pars.UpdatePWDPars;
import com.n.ysb.service.business.pars.WithdrawPars;
import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.business.validator.parsAuthValid.AppKey;
import com.n.ysb.service.thirdparty.vo.BalanceType;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

/**
 *  手机号码 和 登陆密码统一采用AES加解密，再对整体的参数MD5摘要
 */
@RestController
@RequestMapping("receive")
public class ReceiveApi extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(ReceiveApi.class);
	@Autowired
	private ReceiveService receive;
	@Autowired
	private SMSService SMSService;
	@Autowired
	private RegLoginService regLoginService;
	@Autowired
	private MerchantInfoService merchantInfoService;
	@Autowired
	private AppKey appKey;
	@Autowired
	private ParsBasicValidator parsBasicValidator;
	
	@Autowired
	private ClientCommisstionService commisstionService;
	
	@Autowired
	private ClientBalanceService clientBalanceServcie;
	
	@Autowired
	private ClientWithdrawService withdrawService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, HttpServletRequest request) {
//	    Enumeration headerNames = request.getHeaderNames();
//	    
//	    while(headerNames.hasMoreElements()){
//	        Object nextElement = headerNames.nextElement();
//	        String header = request.getHeader(nextElement.toString());
//	        log.info("headNames:{},headValues:{}", nextElement.toString(), header);
//	    }
	    
	    String header = request.getHeader("user-agent");
	    log.info("请求头中user-agent为：{}", header);
		super.initBinder(binder);
        binder.registerCustomEditor(AESString.class, new AESStringEditor(appKey.getAesKey(),header));
	}
	@PostConstruct
	private void init() {
		log.info("ReceiveApi init...");
	}
	
	@RequestMapping(value="sendSMSCode")
	public SimpleResponse sendSMSCode(SendSMSCodePars pars) {
		log.info("sendSMSCode interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = SMSService.sendSMSCode(pars.getMerchantMobile().toString(), pars.getSmsDesc());
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="register")
	public SimpleResponse register(RegisterPars pars) {
		log.info("register interface...");
		
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = regLoginService.register(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="login")
	public SimpleResponse login(LoginPars pars,HttpServletRequest request) {
		log.info("login interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		String header = request.getHeader("user-agent");
		
		Map<String, Object> ret = regLoginService.login(pars,header);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="logout")
	public SimpleResponse logout(LoginPars pars) {
		log.info("logout interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = regLoginService.logout(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="updatePWD")
	public SimpleResponse updatePWD(UpdatePWDPars pars) {
		log.info("updatePWD interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = regLoginService.updatePWD(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="forgetPWD")
	public SimpleResponse forgetPWD(ForgetPWDPars pars) {
		log.info("forgetPWD interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = regLoginService.forgetPWD(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="addCreditCard")
	public SimpleResponse addCreditCard(AddCreditCardPars pars) {
		log.info("addCreditCard interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = merchantInfoService.addCreditCard(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="getCreditCards")
	public SimpleResponse getCreditCards(GetCreditCardPars pars) {
		log.info("getCreditCards interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = merchantInfoService.getCreditCards(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="removeCreditCard")
	public SimpleResponse removeCreditCard(RemoveCreditCardPars pars) {
		log.info("removeCreditCard interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = merchantInfoService.removeCreditCard(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="completeMerchantInfo")
	public SimpleResponse completeMerchantInfo(CompleteMerchantPars pars, 
			@RequestParam("bankCardPhoto") CommonsMultipartFile bankCardPhoto, 
			@RequestParam("idCardPhoto") CommonsMultipartFile idCardPhoto,
			@RequestParam("idCardBackPhoto") CommonsMultipartFile idCardBackPhoto,
			@RequestParam("personPhoto") CommonsMultipartFile personPhoto) throws IllegalStateException, IOException {
		log.info("completeMerchantInfo interface...");
		
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		// process file
		String mobile = pars.getMerchantMobile().toString();
		String classpath = this.getClass().getClassLoader().getResource("/").getPath();
		String projectPath = classpath.substring(0, classpath.indexOf("WEB-INF"));
		String photoPath = projectPath + "upload" + File.separator + mobile + File.separator;
        
		if(!new File(photoPath).exists()) {
			new File(photoPath).mkdirs();
		}
		
		File newBankCardPhoto = new File(photoPath + bankCardPhoto.getOriginalFilename());
		bankCardPhoto.transferTo(newBankCardPhoto);
		File newIdCardPhoto = new File(photoPath + idCardPhoto.getOriginalFilename());
		idCardPhoto.transferTo(newIdCardPhoto);
		File newIdCardBackPhoto = new File(photoPath + idCardBackPhoto.getOriginalFilename());;
		idCardBackPhoto.transferTo(newIdCardBackPhoto);
		File newPersonPhoto = new File(photoPath + personPhoto.getOriginalFilename());;
		personPhoto.transferTo(newPersonPhoto);
		
		// do business
		Map<String, Object> ret = merchantInfoService.completeMerchantInfo(pars, newBankCardPhoto, newIdCardPhoto, newIdCardBackPhoto, newPersonPhoto);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="updateMerchantBankAccount")
	public SimpleResponse updateMerchantBankAccount(UpdateMerchantBankAccountPars pars) {
		log.info("updateMerchantBankAccount interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = merchantInfoService.updateMerchantBankAccount(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="getMerchantInfo")
	public SimpleResponse getMerchantInfo(GetMerchantInfoPars pars) {
		log.info("getMerchantInfo interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = merchantInfoService.getMerchantInfo(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="receive")
	public SimpleResponse receive(ReceivePars pars) {
		log.info("receive interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = receive.receive(pars);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="queryReceive")
	public SimpleResponse queryReceive(QueryReceivePars pars, 
			@RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
	          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
		log.info("queryReceive interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = receive.queryReceive(pars, pageNo, limit);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="computeFee")
	public SimpleResponse computeFee(ComputeFeePars pars) {
		log.info("computeFee interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = receive.computeFee(pars);
		return SimpleResponse.suc(ret);
	}

	@RequestMapping(value="getFee")
	public SimpleResponse getFee(GetFeePars pars) {
		log.info("getFee interface...");
		// valid pars
		Map<String, Object> validMap = parsBasicValidator.valid(pars);
		if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
			return SimpleResponse.suc(validMap);
		}
		
		Map<String, Object> ret = receive.getFee();
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="getCommisstion")
	public SimpleResponse getCommisstion(GetCommisstionPars pars,
	        @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit){
	    log.info("GetCommisstionPars interface...");
	    // valid pars
	    Map<String, Object> validMap = parsBasicValidator.valid(pars);
	    if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
            return SimpleResponse.suc(validMap);
        }
	    
	    Map<String, Object> ret = commisstionService.getCommisstionByRefSign(pars.getYeeCustomerNumber(), pageNo, limit);
	    return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="getBalance")
	public SimpleResponse getBalance(GetBalancePars pars){
	    log.info("getBalance interface...");
	    // valid pars
        Map<String, Object> validMap = parsBasicValidator.valid(pars);
        if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
            return SimpleResponse.suc(validMap);
        }
        
        Map<String, Object> ret = clientBalanceServcie.balanceQuery(pars.getYeeCustomerNumber(), BalanceType.T0_withdraw.getCode());
	    return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="withdraw")
    public SimpleResponse withdraw(WithdrawPars pars){
	    log.info("withdraw interface...");
        // valid pars
        Map<String, Object> validMap = parsBasicValidator.valid(pars);
        if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
            return SimpleResponse.suc(validMap);
        }
        
        Map<String, Object> ret = withdrawService.withdraw(pars);
        return SimpleResponse.suc(ret);
	}
	
	@RequestMapping(value="queryWithdraw")
    public SimpleResponse queryWithdraw(QueryWithdrawPars pars, 
            @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
              @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
        log.info("queryWithdraw interface...");
        // valid pars
        Map<String, Object> validMap = parsBasicValidator.valid(pars);
        if(!Objects.toString(validMap.get(ReturnMap.CODE_LABEL), "").equals(ReturnCode.suc.getCode())) {
            return SimpleResponse.suc(validMap);
        }
        
        Map<String, Object> ret = withdrawService.queryWithdraw(pars, pageNo, limit);
        return SimpleResponse.suc(ret);
    }
	
	
}
