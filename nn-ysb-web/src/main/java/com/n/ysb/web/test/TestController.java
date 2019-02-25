package com.n.ysb.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("test")
public class TestController extends BaseController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IGlobalCfgService globalCfgService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @RequestMapping("getRedisGlobalCfg")
    @ResponseBody
    public SimpleResponse test(){
        NnGlobalCfgVo redisGlobalCfgVo = globalCfgService.getRedisGlobalCfgVo();
        return SimpleResponse.suc(redisGlobalCfgVo);
    }
    
    @RequestMapping("feeSetAll")
    @ResponseBody
    public SimpleResponse feeSetAll(){
        NnMerchantVo merchantByLoginMobile = merchantService.getMerchantByLoginMobile("18911257880");
        logger.info("feeSet start");
        for (int i = 1; i <= 10000; i++) {
            
            yeePayRequestService.feeSet(merchantByLoginMobile);
        }
        logger.info("feeSet end");
        
            return SimpleResponse.suc(merchantByLoginMobile);
        }
}
