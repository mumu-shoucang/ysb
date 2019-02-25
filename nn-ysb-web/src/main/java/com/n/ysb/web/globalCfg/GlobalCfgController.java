package com.n.ysb.web.globalCfg;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("globalCfg")
public class GlobalCfgController extends BaseController {

    @Autowired
    private IGlobalCfgService golbalCfgService;
    
    @RequestMapping("getGlobalCfg")
    @ResponseBody
    public SimpleResponse getGlobalCfg(){
        NnGlobalCfgVo globalCfg = golbalCfgService.getGlobalCfg();
        return SimpleResponse.suc(globalCfg);
    }
    
    @RequestMapping("saveOrUpdateGlobalCfg")
    @ResponseBody
    public SimpleResponse saveOrUpdateGlobalCfg(@Valid NnGlobalCfgVo globalCfgVo, BindingResult bindingResult){
        
        Map<String, String> map = new HashMap<String, String>();
        golbalCfgService.saveOrUpdateGlobalCfg(globalCfgVo);
        map.put("code", "0000");
        
        return SimpleResponse.suc(map);
        
    }
}
