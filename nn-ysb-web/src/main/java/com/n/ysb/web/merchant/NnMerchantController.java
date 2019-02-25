package com.n.ysb.web.merchant;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("/merchant")
public class NnMerchantController extends BaseController{
	
	@Autowired
	private IMerchantService merchantService;
	
	@RequestMapping("getMerchantPage")
	@ResponseBody
	public SimpleResponse getMerchantPage(NnMerchantVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      )throws ParseException{		
		PageInfo<NnMerchant> pageInfo = merchantService.getMerchantPage(vo, pageNo, limit);
		return SimpleResponse.suc(pageInfo);
	}
	
	@RequestMapping("updateFeeSet")
	@ResponseBody
	public SimpleResponse updateFeeSet(NnMerchantVo vo){
		Map<String, Object> updateFeeSet = merchantService.updateFeeSet(vo);
		return SimpleResponse.suc(updateFeeSet);
	}
	
	@RequestMapping("getMerchantDetails")
	@ResponseBody
	public SimpleResponse getMerchantDetails(@RequestParam("id") String id){
		Map<String, Object> map = merchantService.getMerchantDetails(id);
		return SimpleResponse.suc(map);
	}
	
	@RequestMapping("getYeeCustomerNumber")
	@ResponseBody
	public SimpleResponse getYeeCustomerNumber(String idCard){
		NnMerchant merchant = merchantService.getYeeCustomerNumber(idCard);
		return SimpleResponse.suc(merchant);
	}
	
	@RequestMapping("getMerchantByYeecustomerNumber")
    @ResponseBody
	public SimpleResponse getMerchantByYeecustomerNumber(String yeeCustomerNumber){
	    NnMerchant merchant = merchantService.getMerchantByYeecustomerNumber(yeeCustomerNumber);
	    return SimpleResponse.suc(merchant);
	}
	

}
