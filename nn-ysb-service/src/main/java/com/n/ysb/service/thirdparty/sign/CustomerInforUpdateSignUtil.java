package com.n.ysb.service.thirdparty.sign;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.thirdparty.vo.CustomerInforUpdateVo;

public class CustomerInforUpdateSignUtil {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInforUpdateSignUtil.class);
    
    public static Part[] customerInforUpdateSign(CustomerInforUpdateVo customerInforUpdateVo){
        StringBuffer signBuffer = new StringBuffer();
        
        if(customerInforUpdateVo.getModifyType().equals("2")){//银行卡信息
            signBuffer.append(customerInforUpdateVo.getMainCustomerNumber() == null ? "" : customerInforUpdateVo.getMainCustomerNumber())
                      .append(customerInforUpdateVo.getYeeCustomerNumber() == null ? "" : customerInforUpdateVo.getYeeCustomerNumber())
                      .append(customerInforUpdateVo.getBankAccountNo() == null ? "" : customerInforUpdateVo.getBankAccountNo())
                      .append(customerInforUpdateVo.getBankName() == null ? "" : customerInforUpdateVo.getBankName());
        }else if(customerInforUpdateVo.getModifyType().equals("3")){//结算信息
            signBuffer.append(customerInforUpdateVo.getMainCustomerNumber() == null ? "" : customerInforUpdateVo.getMainCustomerNumber())
                      .append(customerInforUpdateVo.getYeeCustomerNumber() == null ? "" : customerInforUpdateVo.getYeeCustomerNumber())
                      .append(customerInforUpdateVo.getRiskReserveDay() == null ? "" : customerInforUpdateVo.getRiskReserveDay())
                      .append(customerInforUpdateVo.getManualSettle() == null ? "" : customerInforUpdateVo.getManualSettle());
        }else if(customerInforUpdateVo.getModifyType().equals("6")){//子商户基本信息
            signBuffer.append(customerInforUpdateVo.getMainCustomerNumber() == null ? "" : customerInforUpdateVo.getMainCustomerNumber())
                      .append(customerInforUpdateVo.getYeeCustomerNumber() == null ? "" : customerInforUpdateVo.getYeeCustomerNumber())
                      .append(customerInforUpdateVo.getBindMobile() == null ? "" : customerInforUpdateVo.getBindMobile())
                      .append(customerInforUpdateVo.getMailStr() == null ? "" : customerInforUpdateVo.getMailStr());
        }
        
        logger.info("子商户信息修改签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), customerInforUpdateVo.getKey());   
        logger.info("子商户信息修改签名为：{}", hmac);
        
        Part[] parts = new CustomerInforUpdatePartsBuilder()
                        .setMainCustomerNumber(customerInforUpdateVo.getMainCustomerNumber())
                        .setCustomerNumber(customerInforUpdateVo.getYeeCustomerNumber())
                        .setModifyType(customerInforUpdateVo.getModifyType())
                        .setBankCardNumber(customerInforUpdateVo.getBankAccountNo())
                        .setBankName(customerInforUpdateVo.getBankName())
                        .setRiskReserveDay(customerInforUpdateVo.getRiskReserveDay())
                        .setManualSettle(customerInforUpdateVo.getManualSettle())
                        .setBindMobile(customerInforUpdateVo.getBindMobile())
                        .setMailStr(customerInforUpdateVo.getMailStr())
                        .setHmac(hmac)
                        .generateParams();
        return parts;
    }
}

class CustomerInforUpdatePartsBuilder {

    private List<Part> parts = new ArrayList<Part>();

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public CustomerInforUpdatePartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setCustomerNumber(
            String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setWhiteList(String whiteList) {
        this.parts.add(new StringPart("whiteList", whiteList == null ? ""
                : whiteList, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setFreezeDays(String freezeDays) {
        this.parts.add(new StringPart("freezeDays", freezeDays == null ? ""
                : freezeDays, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setModifyType(String modifyType) {
        this.parts.add(new StringPart("modifyType", modifyType == null ? ""
                : modifyType, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBankCardNumber(
            String bankCardNumber) {
        this.parts.add(new StringPart("bankCardNumber",
                bankCardNumber == null ? "" : bankCardNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBankName(String bankName) {
        this.parts.add(new StringPart("bankName", bankName == null ? ""
                : bankName, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setRiskReserveDay(
            String riskReserveDay) {
        this.parts.add(new StringPart("riskReserveDay",
                riskReserveDay == null ? "" : riskReserveDay, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setManualSettle(String manualSettle) {
        this.parts.add(new StringPart("manualSettle", manualSettle == null ? ""
                : manualSettle, "UTF-8"));
        return this;
    }
    
    public CustomerInforUpdatePartsBuilder setBindMobile(String bindMobile) {
        this.parts.add(new StringPart("bindMobile", bindMobile == null ? ""
                : bindMobile, "UTF-8"));
        return this;
    }
    
    public CustomerInforUpdatePartsBuilder setMailStr(String mailStr) {
        this.parts.add(new StringPart("mailStr", mailStr == null ? ""
                : mailStr, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setSplitter(String splitter) {
        this.parts.add(new StringPart("splitter", splitter == null ? ""
                : splitter, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setSplitterProfitFee(
            String splitterProfitFee) {
        this.parts.add(new StringPart("splitterProfitFee",
                splitterProfitFee == null ? "" : splitterProfitFee, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBusiness(String business) {
        this.parts.add(new StringPart("business", business == null ? ""
                : business, "UTF-8"));
        return this;
    }
}
