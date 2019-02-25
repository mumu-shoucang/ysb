package com.n.ysb.service.business.core;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BankAliService {

	private static final Logger log = LoggerFactory.getLogger(BankAliService.class);
	
	/**
	 * type: DC/CC 
	 */
	public String getBankCodeFromAli(String bankNo, String type) {

		log.info("getBankCodeFromAli for {}", bankNo);
		
		String bankCode = "";
		String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=" + bankNo + "&cardBinCheck=true";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			int code = client.executeMethod(getMethod);
			if(code == HttpStatus.SC_OK){
				String responseString = getMethod.getResponseBodyAsString();
				log.info("查询银行卡号所属结果：{} - {}", bankNo, responseString);
				Map<String, Object> returnMap = JSONObject.fromObject(responseString);
				// 查询银行卡号所属结果：{"validated":false,"key":"6227470010797805914","stat":"ok","messages":[{"errorCodes":"CARD_BIN_NOT_MATCH","name":"cardNo"}]}
				// {"bank":"ICBC","validated":true,"cardType":"DC","key":"6212260200008079473","messages":[],"stat":"ok"}
				
				if(returnMap.get("validated").toString().equals("true")) {
					String bank = returnMap.get("bank").toString();
					String cardType = returnMap.get("cardType").toString();
					if(type.equals(cardType)) {
						bankCode = bank;
					}
				}
			}else{
				log.info("查询银行卡号所属结果网络异常");
			}
		} catch (HttpException e) {
			log.error("查询银行卡号所属结果网络异常", e);
		} catch (IOException e) {
			log.error("查询银行卡号所属结果网络异常", e);
		} finally{
			getMethod.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		
		return bankCode;
	}
	
	public static void main(String[] args) {
		//  查询银行卡号所属结果：{"bank":"COMM","validated":true,"cardType":"CC","key":"1531296632246-3664-11.233.10.139-639185380","messages":[],"stat":"ok"}
//		BankAliService.getBankCodeFromAli("235852588", "CC");
	}
}
