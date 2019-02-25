package com.n.ysb.service.system;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.system.po.NnResource;
import com.n.ysb.service.system.vo.NnResourceVo;
import com.n.ysb.service.system.vo.ResTreeVo;

public interface IResService {

	PageInfo<NnResource> listModulePage(NnResourceVo vo,int pageNo, int limit);
	
	PageInfo<NnResource> listResPage(NnResourceVo vo,int pageNo, int limit);
	
	List<ResTreeVo> listTreeRes();
	
	boolean addRes(NnResourceVo vo);
	
	boolean updateRes(NnResourceVo vo);
	
	boolean enableRes(String resCode, String parentCode);
	
	boolean disableRes(String resCode);
	
	boolean enableModule(String resCode);
	
	boolean disableModule(String resCode);
	
	NnResource getByCode(String resCode);
}
