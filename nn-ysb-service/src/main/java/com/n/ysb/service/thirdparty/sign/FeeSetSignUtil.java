package com.n.ysb.service.thirdparty.sign;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.ProductType;

public class FeeSetSignUtil {
    private static final Logger logger = LoggerFactory.getLogger(FeeSetSignUtil.class);
    
    public static Part[] feeSetSign(NnMerchantVo merchantVo, NnGlobalCfgVo globalCfgVo, String productType){
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(merchantVo.getYeeCustomerNumber() == null ? "" : merchantVo.getYeeCustomerNumber())
                  .append(merchantVo.getMainCustomerNumber() == null ? "" : merchantVo.getMainCustomerNumber())
                  .append(productType == null ? "" : productType);
        
        if(productType.equals(ProductType.trade_fee.getCode())){
            BigDecimal tradeFee = globalCfgVo.getTradeFee();
            BigDecimal setScale = tradeFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            signBuffer.append(setScale);
        }else if(productType.equals(ProductType.t0_withdraw_fee.getCode())){
            signBuffer.append(globalCfgVo.getT0WithdrawFee() == null ? "" : globalCfgVo.getT0WithdrawFee());
        }else if(productType.equals(ProductType.t0_withdraw_workday_fee.getCode())){
            BigDecimal t0WithdrawWorkdayFee = globalCfgVo.getT0WithdrawWorkdayFee();
            BigDecimal setScale = t0WithdrawWorkdayFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            signBuffer.append(setScale);
        }else if(productType.equals(ProductType.t0_withdraw_nonworkday_fee.getCode())){
            BigDecimal t0WithdrawNonworkdayFee = globalCfgVo.getT0WithdrawNonworkdayFee();
            BigDecimal setScale = t0WithdrawNonworkdayFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            signBuffer.append(setScale);
        }
        
        logger.info("子商户费率设置签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), merchantVo.getKey());   
        logger.info("子商户费率设置签名为：{}", hmac);
        
        FeeSetPartsBuilder feeSetPartsBuilder = new FeeSetPartsBuilder()
                .setCustomerNumber(merchantVo.getYeeCustomerNumber())
                .setGroupCustomerNumber(merchantVo.getMainCustomerNumber())
                .setProductType(productType)
                .setHmac(hmac);
        
        if(productType.equals(ProductType.trade_fee.getCode())){
            BigDecimal tradeFee = globalCfgVo.getTradeFee();
            BigDecimal setScale = tradeFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            feeSetPartsBuilder.setRate(setScale.toString());
        }else if(productType.equals(ProductType.t0_withdraw_fee.getCode())){
            feeSetPartsBuilder.setRate(globalCfgVo.getT0WithdrawFee().toString());
        }else if(productType.equals(ProductType.t0_withdraw_workday_fee.getCode())){
            BigDecimal t0WithdrawWorkdayFee = globalCfgVo.getT0WithdrawWorkdayFee();
            BigDecimal setScale = t0WithdrawWorkdayFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            feeSetPartsBuilder.setRate(setScale.toString());
        }else if(productType.equals(ProductType.t0_withdraw_nonworkday_fee.getCode())){
            BigDecimal t0WithdrawNonworkdayFee = globalCfgVo.getT0WithdrawNonworkdayFee();
            BigDecimal setScale = t0WithdrawNonworkdayFee.divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_UP);
            
            feeSetPartsBuilder.setRate(setScale.toString());
        }
        
        Part[] parts = feeSetPartsBuilder.generateParams();

        return parts;
    }
    
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(0.5678915);
        
        BigDecimal setScale = decimal.setScale(6,BigDecimal.ROUND_HALF_UP);
        System.out.println(setScale);
        
        
    }
    
}

class FeeSetPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }


    public FeeSetPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber", customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setGroupCustomerNumber(String groupCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber", groupCustomerNumber == null ? ""
                : groupCustomerNumber, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setProductType(String productType) {
        this.parts.add(new StringPart("productType", productType == null ? ""
                : productType, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setRate(String rate) {
        this.parts.add(new StringPart("rate",
                rate == null ? "" : rate, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setCfca(String cfca) {
        this.parts
                .add(new StringPart("cfca", cfca == null ? "" : cfca, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
    
}
