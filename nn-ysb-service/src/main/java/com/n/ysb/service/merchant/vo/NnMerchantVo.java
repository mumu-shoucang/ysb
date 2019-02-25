package com.n.ysb.service.merchant.vo;

/**
 * extra info
 */
public class NnMerchantVo extends NnMerchantBaseVo {
    
		private String id;

	    //系统商商户编号
	    private String mainCustomerNumber;
	    
	    private String key;
	    
	    //是否自助结算,N： 隔天自动打款  Y： 自助结算,为空时， 默认为 N。
	    private String manualSettle;
	    
	    //结算周期
	    private String riskReserveDay;
	    
	    //电子邮箱
	    private String mailStr;
	    
	    private String feeSetFlag;

	    private String feeSetType;
	    
	    private String yeeCustomerNumber;

	    private String startTime;
	    
	    private String endTime;
	   
	    public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

	    public String getMainCustomerNumber() {
	        return mainCustomerNumber;
	    }

	    public void setMainCustomerNumber(String mainCustomerNumber) {
	        this.mainCustomerNumber = mainCustomerNumber;
	    }

	    public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public String getManualSettle() {
	        return manualSettle;
	    }

	    public void setManualSettle(String manualSettle) {
	        this.manualSettle = manualSettle;
	    }

	    public String getRiskReserveDay() {
	        return riskReserveDay;
	    }

	    public void setRiskReserveDay(String riskReserveDay) {
	        this.riskReserveDay = riskReserveDay;
	    }

	    public String getMailStr() {
	        return mailStr;
	    }

	    public void setMailStr(String mailStr) {
	        this.mailStr = mailStr;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getFeeSetFlag() {
	        return feeSetFlag;
	    }

	    public void setFeeSetFlag(String feeSetFlag) {
	        this.feeSetFlag = feeSetFlag;
	    }

	    public String getFeeSetType() {
	        return feeSetType;
	    }

	    public void setFeeSetType(String feeSetType) {
	        this.feeSetType = feeSetType;
	    }

		public String getYeeCustomerNumber() {
			return yeeCustomerNumber;
		}

		public void setYeeCustomerNumber(String yeeCustomerNumber) {
			this.yeeCustomerNumber = yeeCustomerNumber;
		}
}