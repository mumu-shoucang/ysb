package com.n.ysb.service.thirdparty.yeepay.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.FeeSetFlag;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.merchantLog.IMerchantLogService;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.thirdparty.ProductType;
import com.n.ysb.service.thirdparty.WithdrawType;
import com.n.ysb.service.thirdparty.sign.BalanceQuerySignUtil;
import com.n.ysb.service.thirdparty.sign.CustomerInforUpdateSignUtil;
import com.n.ysb.service.thirdparty.sign.FeeSetSignUtil;
import com.n.ysb.service.thirdparty.sign.OrderQuerySignUtil;
import com.n.ysb.service.thirdparty.sign.ReceiveSignUtil;
import com.n.ysb.service.thirdparty.sign.RegisterSignUtil;
import com.n.ysb.service.thirdparty.sign.TransferSignUtil;
import com.n.ysb.service.thirdparty.sign.WithDrawQuerySignUtil;
import com.n.ysb.service.thirdparty.sign.WithDrawSignUtil;
import com.n.ysb.service.thirdparty.vo.BalanceType;
import com.n.ysb.service.thirdparty.vo.CustomerInforUpdateVo;
import com.n.ysb.service.thirdparty.vo.ReceiveVo;
import com.n.ysb.service.thirdparty.vo.RegisterVo;
import com.n.ysb.service.thirdparty.vo.TransferVo;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;
import com.n.ysb.service.util.AESUtil;

import net.sf.json.JSONObject;

@Service
public class YeePayRequestServiceImpl implements IYeePayRequestService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    //子商户注册url
    @Value("${yeepay.register.url}")
    private String registerUrl;
    
    //子商户信息修改url
    @Value("${yeepay.customerInfoUpdateUrl.url}")
    private String customerInfoUpdateUrl;
    
    //子商户费率设置url
    @Value("${yeepay.feeSet.url}")
    private String feeSetUrl;
    
    //收款url
    @Value("${yeepay.receive.url}")
    private String receiveUrl;
    
    //交易查询url
    @Value("${yeepay.orderQuery.url}")
    private String orderQueryUrl;
    
    //结算url
    @Value("${yeepay.withDraw.url}")
    private String withDrawUrl;
    
    //结算查询url
    @Value("${yeepay.withDrawQuery.url}")
    private String withDrawQueryUrl;
    
    //异步回调地址
    @Value("${ysb.callBackUrl}")
    private String callBackUrl;
    
    //页面回调地址
    @Value("${ysb.webCallBackUrl}")
    private String webCallBackUrl;
    
    //后台订单手动结算异步回调地址
    @Value("${ysb.withdrawCallBackUrl}")
    private String withdrawCallBackUrl;
    
    //子商户结算异步回调地址
    @Value("${ysb.merchant.withdrawCallBackUrl}")
    private String merchantWithdrawCallBackUrl;
    
    //代理商商户编号
    @Value("${yeepay.mainCustomerNumber}")
    private String mainCustomerNumber;
    
    //商户秘钥
    @Value("${yeepay.key}")
    private String key;
    
    //起结金额,单位元
    @Value("${yeepay.minSettleAmount}")
    private String minSettleAmount;
    //起结金额,单位元
    @Value("${yeepay.linkMan}")
    private String linkMan;
    
    // 转账接口地址
    @Value("${yeepay.transfer.url}")
    private String transferUrl;
    
    // 子商户余额查询接口
    @Value("${yeepay.balanceQuery.url}")
    private String balanceQueryUrl;
    
    @Autowired
    private IGlobalCfgService globalCfgService;
    
    @Autowired
    private IMerchantLogService merchantLogService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Override
    public Map<String, Object> register(RegisterVo registerVo){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        registerVo.setMainCustomerNumber(mainCustomerNumber);
        registerVo.setKey(key);
        registerVo.setMinSettleAmount(minSettleAmount);
        registerVo.setLinkMan(linkMan);
        
        
        Part[] parts = RegisterSignUtil.registerSign(registerVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(registerUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            logger.info("请求易宝注册接口返回码为：{}", code);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝注册接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                return returnMap;
            }else{
                logger.info("请求易宝注册接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public Map<String, Object> customerInforUpdate(CustomerInforUpdateVo customerInforUpdateVo){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        customerInforUpdateVo.setMainCustomerNumber(mainCustomerNumber);
        customerInforUpdateVo.setKey(key);
        
        Part[] parts = CustomerInforUpdateSignUtil.customerInforUpdateSign(customerInforUpdateVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(customerInfoUpdateUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            logger.info("请求易宝子商户信息修改接口返回码为：{}", code);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝子商户信息修改接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                return returnMap;
            }else{
                logger.info("请求易宝子商户信息修改接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public Map<String,Object> tradeFeeSet(NnMerchantVo merchantVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NnGlobalCfgVo globalCfgVo = globalCfgService.getGlobalCfg();
        
        /**设置交易费率开始*/
        Part[] parts = FeeSetSignUtil.feeSetSign(merchantVo, globalCfgVo, ProductType.trade_fee.getCode());
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(feeSetUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝设置交易费率接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝设置交易费率接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            logger.info("HttpException"+e);
            return null;
        } catch (IOException e) {
            logger.info("IOException"+e);
            return null;
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        /**设置交易费率结束*/
    }
    
    static HttpClient client;
    
    static{
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        
        params.setConnectionTimeout(30000);  
        params.setSoTimeout(30000);  
        params.setDefaultMaxConnectionsPerHost(32);  
        params.setMaxTotalConnections(256);  
        
        connectionManager.setParams(params);
        client = new HttpClient(connectionManager);
    }
    
    @Override
    public Map<String,Object> t0WithdrawFeeSet(NnMerchantVo merchantVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NnGlobalCfgVo globalCfgVo = globalCfgService.getGlobalCfg();
        
        /**设置T0自助结算基本费率开始*/
        Part[] parts = FeeSetSignUtil.feeSetSign(merchantVo, globalCfgVo, ProductType.t0_withdraw_fee.getCode());
        
        
//        HttpClient client = new HttpClient(connectionManager);
        PostMethod postMethod = new PostMethod(feeSetUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝T0自助结算基本费率接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝设置T0自助结算基本费率接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            logger.info("HttpException"+e);
            return null;
        } catch (IOException e) {
            logger.info("IOException"+e);
            return null;
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        /**设置T0自助结算基本费率结束*/
    }
    
    @Override
    public Map<String,Object> t0WithdrawWorkdayFeeSet(NnMerchantVo merchantVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NnGlobalCfgVo globalCfgVo = globalCfgService.getGlobalCfg();
        
        /**设置T0自助结算工作日额外费率开始*/
        Part[] parts = FeeSetSignUtil.feeSetSign(merchantVo, globalCfgVo, ProductType.t0_withdraw_workday_fee.getCode());
        
//        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(feeSetUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝T0自助结算工作日额外费接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝设置T0自助结算工作日额外费接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            logger.info("HttpException"+e);
            return null;
        } catch (IOException e) {
            logger.info("IOException"+e);
            return null;
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        /**设置T0自助结算工作日额外费率结束*/
    }
    
    @Override
    public Map<String,Object> t0WithdrawNonworkdayFeeSet(NnMerchantVo merchantVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NnGlobalCfgVo globalCfgVo = globalCfgService.getGlobalCfg();
        
        /**设置T0自助结算非工作日额外费率开始*/
        Part[] parts = FeeSetSignUtil.feeSetSign(merchantVo, globalCfgVo, ProductType.t0_withdraw_nonworkday_fee.getCode());
        
//        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(feeSetUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝T0自助结算非工作日额外费率接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝设置T0自助结算非工作日额外费率接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            logger.info("HttpException"+e);
            return null;
        } catch (IOException e) {
            logger.info("IOException"+e);
            return null;
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        /**设置T0自助结算非工作日额外费率结束*/
    }
    
    @Override
    public Map<String, Object> receive(NnMerchantVo merchantVo, ReceiveVo receiveVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        receiveVo.setCallBackUrl(callBackUrl);
        receiveVo.setWebCallBackUrl(webCallBackUrl);
        receiveVo.setWithdrawCallBackUrl(withdrawCallBackUrl);
        
        Part[] parts = ReceiveSignUtil.receiveSign(merchantVo, receiveVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(receiveUrl);
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝收款接口返回信息为：{}", responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                if(returnMap.get("code").equals("0000")){
                    returnMap.put("url", AESUtil.decrypt(returnMap.get("url").toString(), key));
                }
                
                return returnMap;
            }else{
                logger.info("请求易宝收款接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public JSONObject orderQuery(NnMerchantVo merchantVo, NnOrderVo orderVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NameValuePair[] param = OrderQuerySignUtil.orderQuerySign(merchantVo, orderVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(orderQueryUrl);
        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestBody(param);
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝交易查询接口返回信息为：{}", responseString);
                JSONObject retJson = JSONObject.fromObject(responseString);
                
                return retJson;
            }else{
                logger.info("请求易宝交易查询接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public JSONObject withDraw(NnMerchantVo merchantVo, WithDrawVo withDrawVo, String withdrawType){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        if(WithdrawType.order_hand_withdraw.getCode().equals(withdrawType)){
            withDrawVo.setCallBackUrl(withdrawCallBackUrl);
        }else if(WithdrawType.merchant_withdraw.getCode().equals(withdrawType)){
            withDrawVo.setCallBackUrl(merchantWithdrawCallBackUrl);
        }
        
        NameValuePair[] param = WithDrawSignUtil.withDraw(merchantVo, withDrawVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(withDrawUrl);
        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestBody(param);
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝结算接口返回信息为：{}", responseString);
                JSONObject retJson = JSONObject.fromObject(responseString);
                
                return retJson;
            }else{
                logger.info("请求易宝结算接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public JSONObject withDrawQuery(NnMerchantVo merchantVo, WithDrawVo withDrawVo){
        merchantVo.setMainCustomerNumber(mainCustomerNumber);
        merchantVo.setKey(key);
        
        NameValuePair[] param = WithDrawQuerySignUtil.withDrawQuery(merchantVo, withDrawVo);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(withDrawQueryUrl);
        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestBody(param);
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("请求易宝结算查询接口返回信息为：{}", responseString);
                JSONObject retJson = JSONObject.fromObject(responseString);
                
                return retJson;
            }else{
                logger.info("请求易宝结算查询接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    @Override
    public void feeSet(NnMerchantVo merchantVo){
        //修改商户表状态为费率设置中
        merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_ING.getCode());
        merchantService.updateMerchantFeeInfo(merchantVo);
        
        String feeSetType = "";
        //交易费率，废弃，据易宝的傻叉说，费率固定，无需设置，每笔0.02元
        Map<String, Object> tradeFeeSet = this.tradeFeeSet(merchantVo);
        if(tradeFeeSet.get("code").equals("0000")){
            feeSetType+=ProductType.trade_fee.getCode();
        }
        
        Map<String, Object> t0WithdrawFeeSet = this.t0WithdrawFeeSet(merchantVo);
        if(t0WithdrawFeeSet.get("code").equals("0000")){
            feeSetType+=ProductType.t0_withdraw_fee.getCode();
        }
        
        
        Map<String, Object> t0WithdrawWorkdayFeeSet = this.t0WithdrawWorkdayFeeSet(merchantVo);
        if(t0WithdrawWorkdayFeeSet.get("code").equals("0000")){
            feeSetType+=ProductType.t0_withdraw_workday_fee.getCode();
        }
        
        Map<String, Object> t0WithdrawNonworkdayFeeSet = this.t0WithdrawNonworkdayFeeSet(merchantVo);
        if(t0WithdrawNonworkdayFeeSet.get("code").equals("0000")){
            feeSetType+=ProductType.t0_withdraw_nonworkday_fee.getCode();
        }
        
        ProductType[] values = ProductType.values();
        String productTypeListStr = "";
        for (ProductType productType : values) {
            productTypeListStr+=productType.getCode();
        }
        
        
        
        if(feeSetType.equals(productTypeListStr)){
            merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_SUCCESS.getCode());
        }else{
            merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_FAIL.getCode());
        }
        
        merchantVo.setFeeSetType(feeSetType);
        merchantService.updateMerchantFeeInfo(merchantVo);
        
    }

	@Override
	public Map<String, Object> transferToCustomer(TransferVo par) {
        
        NameValuePair[] param = TransferSignUtil.transfer(par, mainCustomerNumber, key);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(transferUrl);
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestBody(param);
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("{}-易宝转账接口返回信息为：{}", par.getRequestId(), responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝转账接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
	
	@Override
	public Map<String, Object> balanceQuery(String customerNumber, BalanceType balanceType) {
        
        NameValuePair[] param = BalanceQuerySignUtil.buildPars(customerNumber, balanceType.getCode(), mainCustomerNumber, key);
        
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(balanceQueryUrl);
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestBody(param);
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                logger.info("{}-易宝余额查询接口返回信息为：{}", customerNumber, responseString);
                Map<String, Object> returnMap = JSONObject.fromObject(responseString);
                
                return returnMap;
            }else{
                logger.info("请求易宝余额查询接口网络异常");
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
}
