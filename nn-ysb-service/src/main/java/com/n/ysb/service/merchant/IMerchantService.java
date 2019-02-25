package com.n.ysb.service.merchant;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.vo.NnMerchantVo;


public interface IMerchantService {
	
	PageInfo<NnMerchant> getMerchantPage(NnMerchantVo vo,int pageNo, int limit);
	
    NnMerchantVo getMerchantByLoginMobile(String loginMobile);

    void updateMerchantFeeInfo(NnMerchantVo merchantVo);
    
    Map<String, Object> updateFeeSet(NnMerchantVo vo);

    List<NnMerchantVo> getAllMerchant();
    
    Map<String,Object> getMerchantDetails(String id);

    List<NnMerchantVo> getMerchantByRefSign(String refSign);
    
    NnMerchant getYeeCustomerNumber(String idCard);

    NnMerchant getMerchantByYeecustomerNumber(String yeecustomerNumber);
    
}
