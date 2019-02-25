package com.n.ysb.service.system.vo;

public class NnUserVo {

    private String userCode;

    private String userName;

    private String loginName;

    private String loginPwd;
    
    private String refSign; // 推荐人标识
    
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

	public String getRefSign() {
		return refSign;
	}

	public void setRefSign(String refSign) {
		this.refSign = refSign;
	}

}