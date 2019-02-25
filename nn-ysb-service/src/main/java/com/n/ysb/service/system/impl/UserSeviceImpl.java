package com.n.ysb.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.core.IDGenerator;
import com.n.ysb.service.referrer.impl.OnOffStatus;
import com.n.ysb.service.system.IRoleService;
import com.n.ysb.service.system.IUserService;
import com.n.ysb.service.system.PasswordHelper;
import com.n.ysb.service.system.mapper.NnResourceMapper;
import com.n.ysb.service.system.mapper.NnUserMapper;
import com.n.ysb.service.system.mapper.NnUserRoleMapper;
import com.n.ysb.service.system.po.NnResource;
import com.n.ysb.service.system.po.NnUser;
import com.n.ysb.service.system.po.NnUserExample;
import com.n.ysb.service.system.po.NnUserExample.Criteria;
import com.n.ysb.service.system.po.NnUserRole;
import com.n.ysb.service.system.po.NnUserRoleExample;
import com.n.ysb.service.system.vo.Menu;
import com.n.ysb.service.system.vo.NnUserVo;
import com.n.ysb.service.system.vo.RoleTreeVo;

@Service
public class UserSeviceImpl implements IUserService {

	@Autowired
	private NnUserMapper userDao;
	@Autowired
	private NnUserRoleMapper userRoleDao;
	@Autowired
	private NnResourceMapper resDao;
	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private PasswordHelper PasswordHelper;
	@Autowired
	private IRoleService roleService;

	private ConcurrentHashMap<String, Set<String>> prems = new ConcurrentHashMap<>();

	@Override
	public PageInfo<NnUser> listPage(NnUserVo vo, int pageNo, int limit) {
		Page<NnUser> startPage = PageHelper.startPage(pageNo, limit);
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();

		if (StringUtils.isNotBlank(vo.getLoginName())) {
			criteria.andLoginNameEqualTo(vo.getLoginName());
		}
		
		if (StringUtils.isNotBlank(vo.getUserName())) {
			criteria.andUserNameEqualTo(vo.getUserName());
		}

		condition.setOrderByClause("CREATE_DATE desc");

		List<NnUser> list = userDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
	
	@Override
	public PageInfo<NnUser> listAdminPage(NnUserVo vo, int pageNo, int limit) {
		Page<NnUser> startPage = PageHelper.startPage(pageNo, limit);
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();

		if (StringUtils.isNotBlank(vo.getLoginName())) {
			criteria.andLoginNameEqualTo(vo.getLoginName());
		}
		
		if (StringUtils.isNotBlank(vo.getUserName())) {
			criteria.andUserNameEqualTo(vo.getUserName());
		}
		
		criteria.andRefSignIsNull();

		condition.setOrderByClause("CREATE_DATE desc");

		List<NnUser> list = userDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
	
	@Override
	public PageInfo<NnUser> listRefAdminPage(NnUserVo vo, int pageNo, int limit) {
		Page<NnUser> startPage = PageHelper.startPage(pageNo, limit);
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();

		if (StringUtils.isNotBlank(vo.getLoginName())) {
			criteria.andLoginNameEqualTo(vo.getLoginName());
		}
		
		if (StringUtils.isNotBlank(vo.getUserName())) {
			criteria.andUserNameEqualTo(vo.getUserName());
		}

		if (StringUtils.isNotBlank(vo.getRefSign())) {
			criteria.andRefSignEqualTo(vo.getRefSign());
		}
		
		criteria.andRefSignIsNotNull();
		
		condition.setOrderByClause("CREATE_DATE desc");

		List<NnUser> list = userDao.selectByExample(condition);
		return startPage.toPageInfo();
	}

	@Transactional
	@Override
	public boolean addUser(NnUserVo vo, String[] roleCodes) {
		// db user
		NnUser po = new NnUser();
		BeanUtils.copyProperties(vo, po);
		String userCode = IDGenerator.buildRoleCode();
		po.setUserCode(userCode);
		PasswordHelper.encryptPassword(po);
		po.setUserStatus(OnOffStatus.enable.getKey());
		po.setCreateDate(new Date());
		int c = userDao.insertSelective(po);
		// db user-role
		List<NnUserRole> userRoles = new ArrayList<>();
		for (String roleCode : roleCodes) {
			NnUserRole userRole = new NnUserRole();
			userRole.setRoleCode(roleCode);
			userRole.setUserCode(userCode);
			userRoles.add(userRole);
		}
		int cc = userRoleDao.insertUserRoles(userRoles);

		return c == 1 && cc == roleCodes.length ? true : false;
	}

	@Override
	public Map<String, Object> getUserByCode(String userCode) {
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();
		criteria.andUserCodeEqualTo(userCode);
		List<NnUser> users = userDao.selectByExample(condition);

		// checked role
		NnUserRoleExample urcondition = new NnUserRoleExample();
		com.n.ysb.service.system.po.NnUserRoleExample.Criteria urcriteria = urcondition.createCriteria();
		urcriteria.andUserCodeEqualTo(userCode);
		List<NnUserRole> checkedUroles = userRoleDao.selectByExample(urcondition);

		// all role
		List<RoleTreeVo> allRole = roleService.listTreeRole();
		// return role tree
		for (RoleTreeVo allr : allRole) {
			for (NnUserRole checked : checkedUroles) {
				if (allr.getRoleCode().equals(checked.getRoleCode())) {
					allr.setChecked(true);
				}
			}
		}

		// return map
		Map<String, Object> ret = new HashMap<>();
		ret.put("user", users.get(0));
		ret.put("tree", allRole);
		return ret;
	}
	
	@Override
	public Map<String, Object> getOnlyUserByCode(String userCode) {
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();
		criteria.andUserCodeEqualTo(userCode);
		List<NnUser> users = userDao.selectByExample(condition);

		// return map
		Map<String, Object> ret = new HashMap<>();
		ret.put("user", users.get(0));
		return ret;
	}

	@Transactional
	@Override
	public boolean updateUser(String userCode, String userName, String[] roleCodes) {
		NnUser po = new NnUser();
		po.setUserName(userName);

		NnUserExample condition = new NnUserExample();
		com.n.ysb.service.system.po.NnUserExample.Criteria criteria = condition.createCriteria();
		criteria.andUserCodeEqualTo(userCode);
		int c = userDao.updateByExampleSelective(po, condition);

		NnUserRoleExample delcondition = new NnUserRoleExample();
		com.n.ysb.service.system.po.NnUserRoleExample.Criteria delCriteria = delcondition.createCriteria();
		delCriteria.andUserCodeEqualTo(userCode);
		int dc = userRoleDao.deleteByExample(delcondition);

		List<NnUserRole> userRoles = new ArrayList<>();
		for (String roleCode : roleCodes) {
			NnUserRole userRole = new NnUserRole();
			userRole.setRoleCode(roleCode);
			userRole.setUserCode(userCode);
			userRoles.add(userRole);
		}
		int cc = userRoleDao.insertUserRoles(userRoles);

		return c == 1 && cc == roleCodes.length ? true : false;
	}

	@Override
	public NnUser findByLoginName(String loginName) {
		NnUserExample condition = new NnUserExample();
		Criteria criteria = condition.createCriteria();

		criteria.andLoginNameEqualTo(loginName);

		List<NnUser> list = userDao.selectByExample(condition);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Set<String> findRoles(String loginName) {
		Set<String> roles = userDao.findRoles(loginName);
		return roles;
	}

	@Override
	public Set<String> findPermissions(String loginName) {
		Set<String> permissions = prems.get(loginName);
		if (permissions == null) {
			permissions = initPermissions(loginName);
		}
		return permissions;
	}

	@Override
	public Set<String> initPermissions(String loginName) {
		Set<String> permissions = userDao.findPermissions(loginName);
		prems.put(loginName, permissions);
		return permissions;
	}

	@Override
	public List<Menu> getMenuBy(String loginName) {
		List<NnResource> reses = resDao.getMenuByLoginName(loginName);
		List<Menu> menus = new ArrayList<>();
		Map<String, Menu> tempMap = new HashMap<String, Menu>();

		// get menu
		for (NnResource res : reses) {
			if (StringUtils.isBlank(res.getParentCode())) {
				Menu m = new Menu(res.getResourceCode(), res.getResourceName(), "");
				menus.add(m);
				tempMap.put(res.getResourceCode(), m);
			}
		}

		// get sub menu
		for (NnResource res : reses) {

			if (StringUtils.isBlank(res.getParentCode()))
				continue;

			String resParentCode = res.getParentCode();
			Menu match = tempMap.get(resParentCode);
			match.getSubMenus().add(new Menu(res.getResourceCode(), res.getResourceName(), res.getResourceUrl()));
		}

		return menus;
	}

	@Override
	public boolean enableUser(String id) {
		return endisableUser(id, OnOffStatus.enable);
	}

	@Override
	public boolean disableUser(String id) {
		return endisableUser(id, OnOffStatus.disable);
	}

	private boolean endisableUser(String id, OnOffStatus status) {
		NnUser user = new NnUser();
		user.setId(id);
		user.setUserStatus(status.getKey());
		int c = userDao.updateByPrimaryKeySelective(user);
		return c == 1 ? true : false;
	}
	
	@Override
	public boolean updateUserPWD(String userCode, String loginName, String loginPwd) {
		
		NnUser po = new NnUser();
		po.setLoginName(loginName);
		po.setLoginPwd(loginPwd);
		PasswordHelper.encryptPassword(po);

		NnUserExample condition = new NnUserExample();
		com.n.ysb.service.system.po.NnUserExample.Criteria criteria = condition.createCriteria();
		criteria.andUserCodeEqualTo(userCode);
		int c = userDao.updateByExampleSelective(po, condition);

		return c == 1 ? true : false;
	}

}
