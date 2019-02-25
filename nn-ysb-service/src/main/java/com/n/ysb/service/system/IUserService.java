package com.n.ysb.service.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.system.po.NnUser;
import com.n.ysb.service.system.vo.Menu;
import com.n.ysb.service.system.vo.NnUserVo;

public interface IUserService {

	PageInfo<NnUser> listPage(NnUserVo vo,int pageNo, int limit);
	
	PageInfo<NnUser> listAdminPage(NnUserVo vo,int pageNo, int limit);
	
	PageInfo<NnUser> listRefAdminPage(NnUserVo vo,int pageNo, int limit);
	
	boolean addUser(NnUserVo vo, String[] resCodes);
	
	/**
	 * 此方法返回包括 用户信息 和 用户相应的权限两部分信息 
	 */
	Map<String, Object> getUserByCode(String userCode);
	
	Map<String, Object> getOnlyUserByCode(String userCode);
	
	boolean updateUser(String userCode, String userName, String[] roleCodes);

	NnUser findByLoginName(String loginName);
	
	Set<String> findRoles(String loginName);
	
	Set<String> findPermissions(String loginName);
	
	Set<String> initPermissions(String loginName);
	List<Menu> getMenuBy(String loginName);
	
	boolean enableUser(String id);
	
	boolean disableUser(String id);
	
	boolean updateUserPWD(String userCode, String loginName, String loginPwd);
	
}
