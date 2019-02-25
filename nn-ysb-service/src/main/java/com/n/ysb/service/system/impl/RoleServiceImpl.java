package com.n.ysb.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.core.IDGenerator;
import com.n.ysb.service.referrer.impl.OnOffStatus;
import com.n.ysb.service.system.IResService;
import com.n.ysb.service.system.IRoleService;
import com.n.ysb.service.system.mapper.NnRoleMapper;
import com.n.ysb.service.system.mapper.NnRoleResourceMapper;
import com.n.ysb.service.system.po.NnRole;
import com.n.ysb.service.system.po.NnRoleExample;
import com.n.ysb.service.system.po.NnRoleExample.Criteria;
import com.n.ysb.service.system.po.NnRoleResource;
import com.n.ysb.service.system.po.NnRoleResourceExample;
import com.n.ysb.service.system.vo.NnRoleVo;
import com.n.ysb.service.system.vo.ResTreeVo;
import com.n.ysb.service.system.vo.RoleTreeVo;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private NnRoleMapper roleDao;
	@Autowired
	private NnRoleResourceMapper roleResDao;
	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private IResService resService;
	
	@Override
	public PageInfo<NnRole> listPage(NnRoleVo vo, int pageNo, int limit) {
		Page<NnRole> startPage = PageHelper.startPage(pageNo, limit);
		NnRoleExample condition = new NnRoleExample();
    	Criteria criteria = condition.createCriteria();
    	
    	if(StringUtils.isNoneBlank(vo.getRoleName()))
    		criteria.andRoleNameEqualTo(vo.getRoleName());
    	
    	condition.setOrderByClause("ROLE_CODE desc");
		
		List<NnRole> list = roleDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
	
	@Override
	public List<RoleTreeVo> listTreeRole() {
		NnRoleExample condition = new NnRoleExample();
    	Criteria criteria = condition.createCriteria();
    	criteria.andRoleStatusEqualTo(OnOffStatus.enable.getKey());
    	
		List<NnRole> ress = roleDao.selectByExample(condition);
		List<RoleTreeVo> ret = new ArrayList<>();
		for(NnRole role : ress) {
			RoleTreeVo tvo = new RoleTreeVo();
			tvo.setRoleCode(role.getRoleCode());
			tvo.setName(role.getRoleName());
			ret.add(tvo);
		}
		return ret;
	}
	
	@Override
	public Map<String, Object> getRoleByCode(String roleCode) {
		NnRoleExample condition = new NnRoleExample();
		Criteria criteria = condition.createCriteria();
		criteria.andRoleCodeEqualTo(roleCode);
		List<NnRole> roles = roleDao.selectByExample(condition);
		
		// checked res
		NnRoleResourceExample rrcondition = new NnRoleResourceExample();
		com.n.ysb.service.system.po.NnRoleResourceExample.Criteria rrcriteria = rrcondition.createCriteria();
		rrcriteria.andRoleCodeEqualTo(roleCode);
		List<NnRoleResource> checkedRres = roleResDao.selectByExample(rrcondition);
		
		// all res
		List<ResTreeVo> allRes = resService.listTreeRes();
		// return res tree
		for(ResTreeVo allr : allRes) {
			for(NnRoleResource checked : checkedRres) {
				if(allr.getResourceCode().equals(checked.getResourceCode())) {
					allr.setChecked(true);
				}
			}
		}
		
		// return map
		Map<String, Object> ret = new HashMap<>();
		ret.put("role", roles.get(0));
		ret.put("tree", allRes);
		return ret;
	}

	@Transactional
	@Override
	public boolean addRole(String roleName, String[] resCodes) {
		NnRole po = new NnRole();
		String roleCode = IDGenerator.buildRoleCode();
		po.setRoleName(roleName);
		po.setRoleStatus(OnOffStatus.enable.getKey());
		po.setRoleCode(roleCode);
		int c = roleDao.insertSelective(po);
		List<NnRoleResource> rrs = new ArrayList<>();
		for(String resCode : resCodes) {
			NnRoleResource rr = new NnRoleResource();
			rr.setRoleCode(roleCode);
			rr.setResourceCode(resCode);
			rrs.add(rr);
		}
		int cc = roleResDao.insertRoleRess(rrs);
		return c == 1 && cc == resCodes.length ? true : false;
	}
	
	@Transactional
	@Override
	public boolean updateRole(String roleCode, String roleName, String[] resCodes) {
		NnRole po = new NnRole();
		po.setRoleName(roleName);
		
		NnRoleExample condition = new NnRoleExample();
		com.n.ysb.service.system.po.NnRoleExample.Criteria criteria = condition.createCriteria();
		criteria.andRoleCodeEqualTo(roleCode);
		int c = roleDao.updateByExampleSelective(po, condition);
		
		NnRoleResourceExample delcondition = new NnRoleResourceExample();
		com.n.ysb.service.system.po.NnRoleResourceExample.Criteria delCriteria = delcondition.createCriteria();
		delCriteria.andRoleCodeEqualTo(roleCode);
		int dc = roleResDao.deleteByExample(delcondition);
		
		List<NnRoleResource> rrs = new ArrayList<>();
		for(String resCode : resCodes) {
			NnRoleResource rr = new NnRoleResource();
			rr.setRoleCode(roleCode);
			rr.setResourceCode(resCode);
			rrs.add(rr);
		}
		int ic = roleResDao.insertRoleRess(rrs);
		
		return c == 1 && ic == resCodes.length ? true : false;
	}
	
	@Override
	public boolean enableRole(String id) {
		return endisableRole(id, OnOffStatus.enable);
	}
	
	@Override
	public boolean disableRole(String id) {
		return endisableRole(id, OnOffStatus.disable);
	}
	
	private boolean endisableRole(String id, OnOffStatus status) {
		NnRole role = new NnRole();
		role.setId(id);
		role.setRoleStatus(status.getKey());
		int c = roleDao.updateByPrimaryKeySelective(role);
		return c == 1 ? true : false;
	}

}
