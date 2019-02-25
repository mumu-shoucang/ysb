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
import com.n.ysb.service.system.CUser;
import com.n.ysb.service.system.IUserService;
import com.n.ysb.service.system.po.NnUser;
import com.n.ysb.service.system.vo.Menu;
import com.n.ysb.service.system.vo.NnUserVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	private CUser CUser;
	
	@RequestMapping("listPage")
	@ResponseBody
	public SimpleResponse listPage(NnUserVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      ) {
		PageInfo<NnUser> users = userService.listAdminPage(vo, pageNo, limit);
		return SimpleResponse.suc(users);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public SimpleResponse add(NnUserVo vo, @RequestParam("roleCodes[]") String[] roleCodes) {
		boolean isSuc = userService.addUser(vo, roleCodes);
		Map<String, Object> ret = new HashMap<>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("getUserByCode")
	@ResponseBody
	public SimpleResponse getUserByCode(String userCode) {
		Map<String, Object> ret = userService.getUserByCode(userCode);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("getOnlyUserByCode")
	@ResponseBody
	public SimpleResponse getOnlyUserByCode(String userCode) {
		Map<String, Object> ret = userService.getOnlyUserByCode(userCode);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public SimpleResponse update(String userCode, String userName, @RequestParam("roleCodes[]") String[] roleCodes) {
		boolean isSuc = userService.updateUser(userCode, userName, roleCodes);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("updatePWD")
	@ResponseBody
	public SimpleResponse updatePWD(String userCode, String loginName, String loginPwd) {
		boolean isSuc = userService.updateUserPWD(userCode, loginName, loginPwd);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("menu")
	@ResponseBody
	public SimpleResponse menu() {
		NnUser cuser = CUser.cuser();
		
		String loginName = cuser.getLoginName();
		List<Menu> menus = userService.getMenuBy(loginName);
		userService.initPermissions(loginName);
		Map<String, Object> ret = new HashMap<>();
		ret.put("loginName", loginName);
		ret.put("menus", menus);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("enable")
	@ResponseBody
	public SimpleResponse enable(String id) {
		boolean isSuc = userService.enableUser(id);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("disable")
	@ResponseBody
	public SimpleResponse disable(String id) {
		boolean isSuc = userService.disableUser(id);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
}
