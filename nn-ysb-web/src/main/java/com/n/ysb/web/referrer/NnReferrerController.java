package com.n.ysb.web.referrer;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.referrer.IReferrerService;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.vo.NnReferrerVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("/referrer")
public class NnReferrerController extends BaseController{
	
	@Autowired
	private IReferrerService referrerService;

	@Autowired	
    private IGlobalCfgService golbalCfgService;
	
	@RequestMapping("getReferrerPage")
	@ResponseBody
	public SimpleResponse getReferrerPage(NnReferrerVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      )throws ParseException{
		PageInfo<NnReferrer> allReferrer = referrerService.getReferrerPage(vo, pageNo, limit);
		return SimpleResponse.suc(allReferrer);
	}
	
	@RequestMapping("getT0WithdrawFee")
	@ResponseBody
	public SimpleResponse getT0WithdrawFee(){
		NnGlobalCfgVo globalCfg = golbalCfgService.getGlobalCfg();
		return SimpleResponse.suc(globalCfg.getT0WithdrawFee());
	}
	
	@RequestMapping("add")
	@ResponseBody
	public SimpleResponse add(NnReferrer nnReferrer){
		Map<String, Object> map = referrerService.addNnReferrer(nnReferrer);
		return SimpleResponse.suc(map);
	}
	
	@RequestMapping("updateEnable")
	@ResponseBody
	public SimpleResponse updateEnable(NnReferrer po){
		Map<String, Object> map = referrerService.updateEnable(po);
		return SimpleResponse.suc(map);
	}
	
	@RequestMapping("updateDisable")
	@ResponseBody
	public SimpleResponse updateDisable(NnReferrer po){
		Map<String, Object> map = referrerService.updateDisable(po);
		return SimpleResponse.suc(map);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public SimpleResponse update(NnReferrer po){
		Map<String, Object> map = referrerService.update(po);
		return SimpleResponse.suc(null);
	}

}
