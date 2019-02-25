package com.n.ysb.web.register;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n.ysb.service.business.core.RegLoginService;
import com.n.ysb.service.business.core.SMSService;
import com.n.ysb.service.business.pars.RegisterPars;
import com.n.ysb.service.business.validator.parsAuthValid.AppKey;
import com.n.ysb.service.register.H5RegisterVo;
import com.n.ysb.service.util.AESUtil;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@RestController
@RequestMapping("register")
public class RegisterController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    
    @Autowired
    private SMSService SMSService;
    @Autowired
    private RegLoginService regLoginService;
    @Autowired
    private AppKey appKey;
    
    @PostConstruct
    private void init() {
    	log.info(appKey.getRegisterMsg());
    }
    
    @RequestMapping(value="sendSMSCode")
    public SimpleResponse sendSMSCode(String merchantMobile) {
        log.info("h5 sendSMSCode interface...");
        // TODO 参数基本验证！！！
        Map<String, Object> ret = SMSService.sendSMSCode(merchantMobile, appKey.getRegisterMsg());
        return SimpleResponse.suc(ret);
    }
    
    @RequestMapping(value="register")
    public SimpleResponse register(H5RegisterVo registerVo) {
    	// TODO 参数基本验证！！！
        RegisterPars registerpars = new RegisterPars();
        BeanUtils.copyProperties(registerVo, registerpars);
        log.info("h5 register interface...");
        //密码加密
        registerpars.setPwd(AESUtil.encrypt(registerpars.getPwd(), appKey.getAesKey()));
        
        Map<String, Object> ret = regLoginService.register(registerpars);
        return SimpleResponse.suc(ret);
    }

}
