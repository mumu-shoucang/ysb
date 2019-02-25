package com.n.ysb.service.system;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.system.po.NnRole;
import com.n.ysb.service.system.vo.NnRoleVo;
import com.n.ysb.service.system.vo.RoleTreeVo;

public interface IRoleService {

	PageInfo<NnRole> listPage(NnRoleVo vo,int pageNo, int limit);
	
	List<RoleTreeVo> listTreeRole();
	
	boolean addRole(String roleName, String[] resCodes);
	
	boolean updateRole(String roleCode, String roleName, String[] resCodes);
	
	boolean enableRole(String id);
	
	boolean disableRole(String id);
	
	Map<String, Object> getRoleByCode(String roleCode);
}
