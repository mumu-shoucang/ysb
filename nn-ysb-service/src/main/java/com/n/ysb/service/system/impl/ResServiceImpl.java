package com.n.ysb.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.core.IDGenerator;
import com.n.ysb.service.referrer.impl.OnOffStatus;
import com.n.ysb.service.system.IResService;
import com.n.ysb.service.system.mapper.NnResourceMapper;
import com.n.ysb.service.system.po.NnResource;
import com.n.ysb.service.system.po.NnResourceExample;
import com.n.ysb.service.system.po.NnResourceExample.Criteria;
import com.n.ysb.service.system.vo.NnResourceVo;
import com.n.ysb.service.system.vo.ResTreeVo;

@Service
public class ResServiceImpl implements IResService {

	@Autowired
	private NnResourceMapper resDao;
	@Autowired
	private IDGenerator IDGenerator;
	
	@Override
	public PageInfo<NnResource> listModulePage(NnResourceVo vo, int pageNo, int limit) {
		Page<NnResource> startPage = PageHelper.startPage(pageNo, limit);
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	
    	if(StringUtils.isNoneBlank(vo.getResourceName()))
    		criteria.andResourceNameEqualTo(vo.getResourceName());
    	
    	criteria.andParentCodeIsNull();
    	
    	condition.setOrderByClause("RESOURCE_CODE desc");
		
		List<NnResource> list = resDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
	
	@Override
	public PageInfo<NnResource> listResPage(NnResourceVo vo, int pageNo, int limit) {
		Page<NnResource> startPage = PageHelper.startPage(pageNo, limit);
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	
    	if(StringUtils.isNoneBlank(vo.getResourceName()))
    		criteria.andResourceNameEqualTo(vo.getResourceName());
    	
    	if(StringUtils.isNoneBlank(vo.getParentCode()))
    		criteria.andParentCodeEqualTo(vo.getParentCode());
    	
    	criteria.andParentCodeIsNotNull();
    	condition.setOrderByClause("RESOURCE_CODE desc");
		
		List<NnResource> list = resDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
	
	@Override
	public List<ResTreeVo> listTreeRes() {
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	criteria.andResourceStatusEqualTo(OnOffStatus.enable.getKey());
		List<NnResource> ress = resDao.selectByExample(condition);
		List<ResTreeVo> ret = new ArrayList<>();
		for(NnResource res : ress) {
			ResTreeVo tvo = new ResTreeVo();
			tvo.setName(res.getResourceName());
			tvo.setParentCode(res.getParentCode());
			tvo.setResourceCode(res.getResourceCode());
			tvo.setOpen(true);
			ret.add(tvo);
		}
		return ret;
	}
	
	@Override
	public NnResource getByCode(String resCode) {
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	criteria.andResourceCodeEqualTo(resCode);
		List<NnResource> ress = resDao.selectByExample(condition);
		return ress != null && ress.size() == 1 ? ress.get(0) : null;
	}

	@Override
	public boolean addRes(NnResourceVo vo) {
		NnResource po = new NnResource();
		BeanUtils.copyProperties(vo, po);
		po.setResourceCode(IDGenerator.buildResCode());
		po.setResourceStatus(OnOffStatus.enable.getKey());
		int c = resDao.insertSelective(po);
		return c == 1? true : false;
	}
	
	@Override
	public boolean updateRes(NnResourceVo vo) {
		NnResource po = new NnResource();
		BeanUtils.copyProperties(vo, po);
		
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	criteria.andResourceCodeEqualTo(vo.getResourceCode());
		int c = resDao.updateByExampleSelective(po, condition);
		return c == 1? true : false;
	}
	
	@Override
	public boolean enableRes(String resCode, String parentCode) {
		boolean c = updateResStatus(OnOffStatus.enable, resCode);
		boolean pc = updateResStatus(OnOffStatus.enable, parentCode);
		return c && pc ? true : false;
	}
	
	@Override
	public boolean disableRes(String resCode) {
		return updateResStatus(OnOffStatus.disable, resCode);
	}
	
	@Override
	public boolean enableModule(String resCode) {
		int c = resDao.enableModule(resCode);
		return c > 0 ? true : false;
	}
	
	@Override
	public boolean disableModule(String resCode) {
		int c = resDao.disableModule(resCode);
		return c > 0 ? true : false;
	}
	
	private boolean updateResStatus(OnOffStatus status, String resCode) {
		NnResource po = new NnResource();
		po.setResourceStatus(status.getKey());
		
		NnResourceExample condition = new NnResourceExample();
    	Criteria criteria = condition.createCriteria();
    	criteria.andResourceCodeEqualTo(resCode);
		int c = resDao.updateByExampleSelective(po, condition);
		return c == 1? true : false;
	} 

}
