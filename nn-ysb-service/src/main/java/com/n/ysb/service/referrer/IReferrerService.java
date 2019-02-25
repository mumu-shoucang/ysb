package com.n.ysb.service.referrer;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.vo.NnReferrerVo;

public interface IReferrerService {

	
	PageInfo<NnReferrer> getReferrerPage(NnReferrerVo vo,int pageNo, int limit);
	
	Map<String, Object> addNnReferrer(NnReferrer po);
	
	Map<String, Object> updateEnable(NnReferrer po);
	
	Map<String, Object> updateDisable(NnReferrer po);
	
	Map<String, Object> update(NnReferrer po);
	
	List<NnReferrer> listAllActiveRef();
	
	/**
	 * 此方法有缓存，仅适用于取值不变的字段，比如推荐人标识 
	 */
	NnReferrer getById(String id);
}
