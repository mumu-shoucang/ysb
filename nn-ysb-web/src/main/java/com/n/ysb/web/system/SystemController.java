package com.n.ysb.web.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.system.IUserService;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("sys")
public class SystemController extends BaseController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping("login")
	@ResponseBody
	public SimpleResponse login(String loginName, String loginPwd) {
		try {
			 UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);  
			 Subject subject = SecurityUtils.getSubject();
			 subject.login(token);
			 userService.initPermissions(loginName);
			 return SimpleResponse.suc(ReturnMap.suc());
			 
		} catch(IncorrectCredentialsException | UnknownAccountException ex) {
			return SimpleResponse.suc(ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "用户名/密码错误"));
		} catch(LockedAccountException ex) {
			return SimpleResponse.suc(ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "账号被锁定"));
		}
	}
	
}
