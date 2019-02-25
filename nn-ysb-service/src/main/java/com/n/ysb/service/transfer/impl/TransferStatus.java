package com.n.ysb.service.transfer.impl;

import com.n.ysb.service.business.OrderStatus;

public enum TransferStatus {
	
	suc("1","成功"), fail("0","失败"), untransfer("2","未转");
	
	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	private TransferStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(String code){
        for(TransferStatus of : TransferStatus.values()){
            if(of.code.equals(code)){
                return of.desc;
            }
        }
        return null;
    }

}
