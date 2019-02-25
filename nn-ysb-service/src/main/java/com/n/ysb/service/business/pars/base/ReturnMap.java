package com.n.ysb.service.business.pars.base;

import java.util.HashMap;
import java.util.Map;

public class ReturnMap {
	
	private final static String DATA_LABEL = "data";
	public final static String CODE_LABEL = "code";
	private final static String DESC_LABEL = "desc";

	public static Map<String, Object> New(String code, String desc) {
		Map<String, Object> map = new HashMap<>();
		map.put(CODE_LABEL, code);
		map.put(DESC_LABEL, desc);
		map.put(DATA_LABEL, null);
		return map;
	}
	
	public static Map<String, Object> New(String code, String desc, Object body) {
		Map<String, Object> map = new HashMap<>();
		map.put(CODE_LABEL, code);
		map.put(DESC_LABEL, desc);
		map.put(DATA_LABEL, body);
		return map;
	}
	
	public static Map<String, Object> receiveSuc(String orderNo) {
		Map<String, Object> ret = New(ReturnCode.suc.getCode(), ReturnCode.suc.getDesc());
		Map<String, Object> data = new HashMap<>();
		data.put("url", orderNo);
		ret.put(DATA_LABEL, data);
		return ret;
	}
	
	public static Map<String, Object> suc(Object data) {
		Map<String, Object> ret = New(ReturnCode.suc.getCode(), ReturnCode.suc.getDesc());
		ret.put(DATA_LABEL, data);
		return ret;
	}
	
	public static Map<String, Object> suc() {
		Map<String, Object> ret = New(ReturnCode.suc.getCode(), ReturnCode.suc.getDesc());
		ret.put(DATA_LABEL, new HashMap<>());
		return ret;
	}
	
	public static Map<String, Object> fail() {
		Map<String, Object> ret = New(ReturnCode.fail.getCode(), ReturnCode.fail.getDesc());
		return ret;
	}
	
	public static Map<String, Object> netError() {
		Map<String, Object> ret = New(ReturnCode.net_error.getCode(), ReturnCode.net_error.getDesc());
		return ret;
	}
	
	public static Map<String, Object> queryBalanceSuc(String balance) {
        Map<String, Object> ret = New(ReturnCode.suc.getCode(), ReturnCode.suc.getDesc());
        Map<String, Object> data = new HashMap<>();
        data.put("balance", balance);
        ret.put(DATA_LABEL, data);
        return ret;
    }
}

//{"code":"响应码","desc":"响应描述", "data":{"sign":"", 其他业务数据}}
