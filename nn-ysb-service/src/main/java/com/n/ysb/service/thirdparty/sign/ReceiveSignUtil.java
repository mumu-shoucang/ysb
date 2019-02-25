package com.n.ysb.service.thirdparty.sign;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.AutoWithdraw;
import com.n.ysb.service.thirdparty.vo.ReceiveVo;

public class ReceiveSignUtil {

    private static final Logger logger = LoggerFactory.getLogger(RegisterSignUtil.class);
    
    public static Part[] receiveSign(NnMerchantVo merchantVo, ReceiveVo receiveVo){
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append("B")
                  .append(merchantVo.getMainCustomerNumber() == null ? "" : merchantVo.getMainCustomerNumber())
                  .append(merchantVo.getYeeCustomerNumber() == null ? "" : merchantVo.getYeeCustomerNumber())
                  .append(receiveVo.getOrderAmt() == null ? "" : receiveVo.getOrderAmt())
                  .append(receiveVo.getMcc() == null ? "" : receiveVo.getMcc())
                  .append(receiveVo.getOrderNo() == null ? "" : receiveVo.getOrderNo())
                  .append(receiveVo.getCallBackUrl() == null ? "" : receiveVo.getCallBackUrl())
                  .append(receiveVo.getWebCallBackUrl() == null ? "" : receiveVo.getWebCallBackUrl())
                  .append(receiveVo.getCreditCardNo() == null ? "" : receiveVo.getCreditCardNo());
        
        logger.info("收款签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), merchantVo.getKey());   
        logger.info("收款签名为：{}", hmac);
        
        Part[] parts = new ReceviePartsBuiler()
                                .setMainCustomerNumber(merchantVo.getMainCustomerNumber())
                                .setCustomerNumber(merchantVo.getYeeCustomerNumber())
                                .setRequestId(receiveVo.getOrderNo())
                                .setSource("B")
                                .setAmount(receiveVo.getOrderAmt().toString())
                                .setMcc(receiveVo.getMcc())
                                .setCallBackUrl(receiveVo.getCallBackUrl())
                                .setWebCallBackUrl(receiveVo.getWebCallBackUrl())
                                .setPayerBankAccountNo(receiveVo.getCreditCardNo())
                                .setAutoWithdraw(AutoWithdraw.TRUE.getCode())
                                .setWithdrawCallBackUrl(receiveVo.getWithdrawCallBackUrl())
                                .setHamc(hmac)
                                .generateParams();
        return parts;
    }
}

class ReceviePartsBuiler {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public ReceviePartsBuiler setSource(String source) {
        this.parts.add(new StringPart("source", source == null ? "" : source,
                "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setRequestId(String requestId) {
        this.parts.add(new StringPart("requestId", requestId == null ? ""
                : requestId, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setAmount(String amout) {
        this.parts.add(new StringPart("amount", amout == null ? "" : amout,
                "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMcc(String mcc) {
        this.parts.add(new StringPart("mcc", mcc == null ? "" : mcc, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMobileNumber(String mobileNumber) {
        this.parts.add(new StringPart("mobileNumber", mobileNumber == null ? ""
                : mobileNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCallBackUrl(String callBackUrl) {
        this.parts.add(new StringPart("callBackUrl", callBackUrl == null ? ""
                : callBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setWebCallBackUrl(String webCallBackUrl) {
        this.parts.add(new StringPart("webCallBackUrl",
                webCallBackUrl == null ? "" : webCallBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setSmgCallBackUrl(String smgCallBackUrl) {
        this.parts.add(new StringPart("smgCallBackUrl",
                smgCallBackUrl == null ? "" : smgCallBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setPayerBankAccountNo(String payerBankAccountNo) {
        this.parts.add(new StringPart("payerBankAccountNo",
                payerBankAccountNo == null ? "" : payerBankAccountNo, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setHamc(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCfca(String cfca) {
        this.parts
                .add(new StringPart("cfca", cfca == null ? "" : cfca, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setDescription(String description) {
        this.parts.add(new StringPart("description", description == null ? ""
                : description, "UTF-8"));
        return this;
    }


    public ReceviePartsBuiler setAutoWithdraw(String autoWithdraw) {
        this.parts.add(new StringPart("autoWithdraw",
                autoWithdraw == null ? "" : autoWithdraw, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setWithdrawCardNo(String withdrawCardNo) {
        this.parts.add(new StringPart("withdrawCardNo",
                withdrawCardNo == null ? "" : withdrawCardNo, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCustomTradeFee(String customTradeFee) {
        this.parts.add(new StringPart("customTradeFee",
                customTradeFee == null ? "" : customTradeFee, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setWithdrawCallBackUrl(String withdrawCallBackUrl) {
        this.parts.add(new StringPart("withdrawCallBackUrl",
                withdrawCallBackUrl == null ? "" : withdrawCallBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setProductVersion(String productVersion) {
        this.parts.add(new StringPart("productVersion",
                productVersion == null ? "" : productVersion, "UTF-8"));
        return this;
    }

}
