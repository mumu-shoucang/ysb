package com.n.ysb.service.thirdparty.vo;

import com.n.ysb.service.merchant.vo.NnMerchantVo;

public class CustomerInforUpdateVo extends NnMerchantVo {

    //修改类型
    private String modifyType;
    
    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }
}
