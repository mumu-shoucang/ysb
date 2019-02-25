package com.n.ysb.service.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.n.ysb.service.system.po.NnUser;

@Service
public class CUser {

	public NnUser cuser() {
		Subject subject = SecurityUtils.getSubject();
		NnUser cuser = (NnUser)subject.getPrincipal();
		return cuser;
	}
	
	public boolean isRef() {
		NnUser cuser = this.cuser();
		return StringUtils.isBlank(cuser.getRefSign()) ? false : true; 
	}
	
	public String cuserRefSign() {
		NnUser cuser = this.cuser();
		if(cuser == null)
			return "";
		
		String refSign = cuser.getRefSign();
		if(StringUtils.isBlank(refSign))
			return "";
		return refSign;
	}
	
}
