package com.n.ysb.service.transfer.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.transfer.mapper.NnTransferLogMapper;
import com.n.ysb.service.transfer.po.NnTransferLog;
import com.n.ysb.service.transfer.po.NnTransferLogExample;
import com.n.ysb.service.transfer.po.NnTransferLogExample.Criteria;
import com.n.ysb.service.transfer.vo.TransferLogVo;

@Service
public class TransferLogService {
	
	@Autowired
	private NnTransferLogMapper transferLogDao;

	public PageInfo<NnTransferLog> listPage(TransferLogVo vo, int pageNo, int limit) {
		Page<NnTransferLog> startPage = PageHelper.startPage(pageNo, limit);
		NnTransferLogExample condition = new NnTransferLogExample();
		Criteria criteria = condition.createCriteria();

		if (StringUtils.isNotBlank(vo.getRefSign())) {
			criteria.andRefSignEqualTo(vo.getRefSign());
		}
		
		if (StringUtils.isNotBlank(vo.getOpUser())) {
			criteria.andOpUserEqualTo(vo.getOpUser());
		}
		
		if(StringUtils.isNotBlank(vo.getOpDate())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date opStartDate = null;
            Date opEndDate = null;
            try {
                opStartDate = format.parse(vo.getOpDate() + " 00:00:00");
                opEndDate = format.parse(vo.getOpDate() + " 23:59:59");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            criteria.andOpDateGreaterThanOrEqualTo(opStartDate);
            criteria.andOpDateLessThanOrEqualTo(opEndDate);
        }
		
		if (StringUtils.isNotBlank(vo.getTransferStatus())) {
			criteria.andTransferStatusEqualTo(vo.getTransferStatus());
		}
		
		if(StringUtils.isNotBlank(vo.getStartDate())){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = null;
            try {
                parse = format.parse(vo.getStartDate() + " 00:00:00");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            criteria.andTransferDateGreaterThanOrEqualTo(parse);
        }
        
        if(StringUtils.isNotBlank(vo.getEndDate())){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = null;
            try {
                parse = format.parse(vo.getEndDate() + " 23:59:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andTransferDateLessThanOrEqualTo(parse);
        }
		

		condition.setOrderByClause("OP_DATE desc");

		List<NnTransferLog> list = transferLogDao.selectByExample(condition);
		return startPage.toPageInfo();
	}
}
