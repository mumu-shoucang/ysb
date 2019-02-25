package com.n.ysb.service.system.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.n.ysb.service.referrer.impl.OnOffStatus;
import com.n.ysb.service.system.IUserService;
import com.n.ysb.service.system.po.NnUser;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		NnUser cuser = (NnUser)principals.getPrimaryPrincipal();
		String loginName = cuser.getLoginName();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(loginName));
        authorizationInfo.setStringPermissions(userService.findPermissions(loginName));
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginName = (String)token.getPrincipal();
		NnUser user = userService.findByLoginName(loginName);
		if(user == null) {
            throw new UnknownAccountException();
        }

        if(user.getUserStatus().equals(OnOffStatus.disable.getKey())) {
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getLoginPwd(), //密码
                ByteSource.Util.bytes(user.getLoginName() + user.getUserSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
	}

}
