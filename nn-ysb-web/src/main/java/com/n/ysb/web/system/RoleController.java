package com.n.ysb.web.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.system.IRoleService;
import com.n.ysb.service.system.po.NnRole;
import com.n.ysb.service.system.vo.NnRoleVo;
import com.n.ysb.service.system.vo.RoleTreeVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("listPage")
	@ResponseBody
	public SimpleResponse listModulePage(NnRoleVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      ) {
		PageInfo<NnRole> users = roleService.listPage(vo, pageNo, limit);
		return SimpleResponse.suc(users);
	}
	
	@RequestMapping("listTreeRole")
	@ResponseBody
	public SimpleResponse listTreeRole() {
		List<RoleTreeVo> ret = roleService.listTreeRole();
		return SimpleResponse.suc(ret);
	}

	@RequestMapping("getRoleByCode")
	@ResponseBody
	public SimpleResponse getRoleByCode(String roleCode) {
		Map<String, Object> ret = roleService.getRoleByCode(roleCode);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("addRole")
	@ResponseBody
	public SimpleResponse addRole(String roleName, @RequestParam("resCodes[]") String[] resCodes) {
		boolean isSuc = roleService.addRole(roleName, resCodes);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public SimpleResponse updateRole(String roleCode, String roleName, @RequestParam("resCodes[]") String[] resCodes) {
		boolean isSuc = roleService.updateRole(roleCode, roleName, resCodes);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("enable")
	@ResponseBody
	public SimpleResponse enable(String id) {
		boolean isSuc = roleService.enableRole(id);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("disable")
	@ResponseBody
	public SimpleResponse disable(String id) {
		boolean isSuc = roleService.disableRole(id);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
}
