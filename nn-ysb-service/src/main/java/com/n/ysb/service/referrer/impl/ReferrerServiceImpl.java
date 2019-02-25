package com.n.ysb.service.referrer.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.referrer.IReferrerService;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;
import com.n.ysb.service.referrer.po.NnReferrerExample.Criteria;
import com.n.ysb.service.referrer.vo.NnReferrerVo;

@Service
public class ReferrerServiceImpl implements IReferrerService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private NnReferrerMapper nnReferrerMapper;
	
	@Autowired
	private IMerchantService merchantService;

	@Override
	public Map<String, Object> addNnReferrer(NnReferrer po) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuc = false;

		po.setRefCode(randomNo());
		po.setCreateDate(new Date());
		po.setRefStatus(OnOffStatus.enable.getKey());
		//根据身份证号查询子商户
		NnMerchant merchant = merchantService.getYeeCustomerNumber(po.getIdCard());
		if(merchant!=null && StringUtils.isNotBlank(merchant.getYeeCustomerNumber())){
		    log.info("创建推荐人时，发现子商户{}，将子商户编码{}更新到推荐人表", merchant.getLoginMobile(), merchant.getYeeCustomerNumber());
		    po.setYeeCustomerNumber(merchant.getYeeCustomerNumber());
		}
		
		int insert = nnReferrerMapper.insertSelective(po);

		if (insert > 0) {
			isSuc = true;
			map.put("添加成功", isSuc);
		} else {
			map.put("添加失败", isSuc);
		}
		log.info("插入推荐人-:{}，成功/失败-{}",
				po.getName() + "////" + po.getMobile() + "///" + po.getRefSign() + "///" + po.getRefCode() + "///"
						+ po.getRefStatus() + "///" + po.getCreateDate() + "///" + po.getCommissionRate()+
						"///" + po.getIdCard()+"///" +po.getCkBankCard()+"///" + po.getBasicCommission(),
				isSuc);

		return map;
	}

	public String randomNo() {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			str.append(random.nextInt(10));
		}
		log.info("随机生成邀请码-:{}", str, toString());
		List<NnReferrer> byRefCode = nnReferrerMapper.getByRefCode(str.toString());
		if (byRefCode.size() > 0) {
			log.info("推荐人邀请码重复-:{}", str.toString());
			randomNo();
		}

		return str.toString();
	}

	@Override
	public Map<String, Object> update(NnReferrer po) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuc = false;
		
		//根据身份证号查询子商户
        NnMerchant merchant = merchantService.getYeeCustomerNumber(po.getIdCard());
        if(merchant!=null && StringUtils.isNotBlank(merchant.getYeeCustomerNumber())){
            log.info("创建推荐人时，发现子商户{}，将子商户编码{}更新到推荐人表", merchant.getLoginMobile(), merchant.getYeeCustomerNumber());
            po.setYeeCustomerNumber(merchant.getYeeCustomerNumber());
        }
		
		int key = nnReferrerMapper.updateByPrimaryKeySelective(po);
		if (key > 0) {
			isSuc = true;
			map.put("修改成功", isSuc);
		} else {
			map.put("修改失败", isSuc);
		}
		log.info("修改数据-:{}，成功/失败-{}", po.getId() + "////" + po.getMobile() + "///" + po.getCommissionRate()+
				"///" + po.getIdCard()+"///" +po.getCkBankCard()+"///" + po.getBasicCommission(), isSuc);

		return map;
	}

	@Override
	public Map<String, Object> updateEnable(NnReferrer po) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuc = false;
		int key = nnReferrerMapper.updateByPrimaryKeySelective(po);
		if (key > 0) {
			isSuc = true;
			map.put("启用成功", isSuc);
		} else {
			map.put("启用失败", isSuc);
		}
		log.info("启用-:{}，成功/失败-{}", po.getId() + "////" + po.getRefStatus() + "///", isSuc);

		return map;
	}

	@Override
	public Map<String, Object> updateDisable(NnReferrer po) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuc = false;
		int key = nnReferrerMapper.updateByPrimaryKeySelective(po);
		if (key > 0) {
			isSuc = true;
			map.put("禁用成功", isSuc);
		} else {
			map.put("禁用失败", isSuc);
		}
		log.info("禁用-:{}，成功/失败-{}", po.getId() + "////" + po.getRefStatus() + "///", isSuc);

		return map;
	}

	@Override
	public PageInfo<NnReferrer> getReferrerPage(NnReferrerVo vo, int pageNo, int limit) {
		Page<NnReferrer> startPage = PageHelper.startPage(pageNo, limit);
		NnReferrerExample example = new NnReferrerExample();
		Criteria criteria = example.createCriteria();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;

		// 推荐人标识
		if (StringUtils.isNotBlank(vo.getRefSign())) {
			criteria.andRefSignEqualTo(vo.getRefSign());
		}

		// 推荐人状态
		if (StringUtils.isNotBlank(vo.getRefStatus())) {
			criteria.andRefStatusEqualTo(vo.getRefStatus());
		}

		// 创建时间
		try {
			if (StringUtils.isNotBlank(vo.getStartTime())) {
				startTime = sdf.parse(vo.getStartTime() + " 00:00:00");
				criteria.andCreateDateGreaterThanOrEqualTo(startTime);
			}
			if (StringUtils.isNotBlank(vo.getEndTime())) {
				endTime = sdf.parse(vo.getEndTime() + " 23:59:59");
				criteria.andCreateDateLessThanOrEqualTo(endTime);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		example.setOrderByClause("CREATE_DATE desc");

		List<NnReferrer> selectByExample = nnReferrerMapper.selectByExample(example);
		log.info("===================推荐人管理分页查询:" + selectByExample + "=========================");
		
		return startPage.toPageInfo();
	}

	@Override
	public List<NnReferrer> listAllActiveRef() {
		List<NnReferrer> ret = new ArrayList<>();
		NnReferrerVo condition = new NnReferrerVo();
		condition.setRefStatus(OnOffStatus.enable.getKey());
		PageInfo<NnReferrer> refs = getReferrerPage(condition, 0, 0);
		ret = refs.getList();
		log.info("查询所有active的推荐人-{}", ret.size());
		return ret;
	}
	
	@Cacheable(value = "default", key = "#id")
	@Override
	public NnReferrer getById(String id) {
		NnReferrer ref = nnReferrerMapper.selectByPrimaryKey(id);
		return  ref;
	}

}
