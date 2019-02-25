package com.n.ysb.service.transfer.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.core.IDGenerator;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.statistics.mapper.NnStatisticsMapper;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.po.NnStatisticsExample;
import com.n.ysb.service.system.CUser;
import com.n.ysb.service.thirdparty.sms.SMS2;
import com.n.ysb.service.thirdparty.vo.TransferVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;
import com.n.ysb.service.transfer.mapper.NnTransferLogMapper;
import com.n.ysb.service.transfer.mapper.NnTransferQueMapper;
import com.n.ysb.service.transfer.po.NnTransferLog;
import com.n.ysb.service.transfer.po.NnTransferLogExample;
import com.n.ysb.service.transfer.po.NnTransferQue;
import com.n.ysb.service.transfer.po.NnTransferQueExample;
import com.n.ysb.service.transfer.po.NnTransferQueExample.Criteria;

@Service
public class TransferService {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IYeePayRequestService yeepayService;
	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private NnTransferLogMapper transferLogDao;
	@Autowired
	private NnTransferQueMapper transferQueDao;
	@Autowired
	private CUser CUser;
	@Autowired
	private NnStatisticsMapper statDao;
	@Autowired
	private SMS2 sms2;
	@Autowired
	private BusRedis busRedis;
	
	@Value("${mainCustomerNumber.mobile}")
    private String mainMobile;
	
	/**
	 * 发送转账短信验证码
	 */
	public Map<String, Object> sendSMSCode(String smsDesc) {
		
		log.info("send smsCode for moible {}", mainMobile);
		
		String smsCode = buildSmsCode();
		String msg = "短信验证码:" + smsCode + "，20分钟内有效。";
		msg = msg + "登录账户" + CUser.cuser().getLoginName() + "正在进行佣金转账操作。";
		if(StringUtils.isNotBlank(smsDesc)) {
			msg += smsDesc;
		}
		sms2.sendSMSCode(mainMobile, msg);
		busRedis.setTransferSmsCode(mainMobile, smsCode);
		return ReturnMap.suc();
	}
	
	private String buildSmsCode(){
	    String smsCode ="";
	    while(true){
	        smsCode = new Random().nextInt(1000000) + "";
	        if(smsCode.length()==6){
	            break;
	        }
	    }
	    return smsCode;
	}
	
	public boolean checkSmsCode(String smsCode) {
		String redisSmsCode = busRedis.getTransferSmsCode(mainMobile);
		return redisSmsCode.equals(smsCode);
	}
	
	/**
	 * 批量佣金转账 
	 */
	public Map<String, Object> batchTransfer(List<NnStatistics> statPos) {
		
		Map<String, Object> retMap = new HashMap<>();
		
		int succ = 0;
		int failc = 0;
		
		for(NnStatistics po : statPos) {
			Map<String, Object> sm = this.singleTransfer(po, false);
			if((boolean)sm.get("isSuc")) {
				succ++;
			} else {
				failc++;
			}
		}
		retMap.put("desc", String.format("本转账批次总共%d条数据，其中成功%d条，失败%d条", statPos.size(), succ, failc));
		return retMap;
	}
	
	/**
	 * 单条佣金转账 
	 */
	public Map<String, Object> singleTransfer(NnStatistics po, boolean isFailover) {

		Map<String, Object> retMap = new HashMap<>();
		retMap.put("isSuc", false);
		retMap.put("desc", "");
		
		BigDecimal transferAmt = po.getTradeCommisstion();
		String customerNumber = po.getYeeCustomerNumber();
		String refSign = po.getStatisticsType();
		
		boolean canTransfer = canTransfer(refSign, po.getStatisticsDate());
		if(!canTransfer) {
			log.info("推荐人-{}-{}-{}已经转账过，不能再重复发起", refSign, customerNumber, po.getStatisticsDate());
		}
		
		log.info("推荐人-{}-{}-{} 发起转账请求", refSign, customerNumber, po.getStatisticsDate());
		Map<String, Object> ret = yeepayService.transferToCustomer(new TransferVo(IDGenerator.buildTransferReqID(), customerNumber, transferAmt.toString()));
		String retCode = ObjectUtils.toString(ret.get("code"), "");
		
		NnTransferLog transferLog = new NnTransferLog();
		transferLog.setRefSign(refSign);
		transferLog.setTransferAmt(transferAmt);
		transferLog.setTransferDate(po.getStatisticsDate());
		transferLog.setOpUser(CUser.cuser().getLoginName());
		transferLog.setOpDate(new Date());
		transferLog.setYeeCustomerNumber(customerNumber);
		try {
			if("0000".equals(retCode)) { // 成功
				log.info("推荐人-{}-{}-{} 发起转账请求成功", refSign, customerNumber, po.getStatisticsDate());
				transferLog.setTransferStatus(TransferStatus.suc.getCode());
				transferLog.setRemark("请求成功");
				
				if(isFailover) {
					// 更新 转账记录
					NnTransferLogExample condition = new NnTransferLogExample();
					com.n.ysb.service.transfer.po.NnTransferLogExample.Criteria logCriteria = condition.createCriteria();
					logCriteria.andRefSignEqualTo(refSign);
					logCriteria.andTransferDateEqualTo(po.getStatisticsDate());
					int c = transferLogDao.updateByExampleSelective(transferLog, condition);
					if(c != 1) {
						log.warn("推荐人-{}-{}-{} 更新转账记录失败：{}", refSign, customerNumber, po.getStatisticsDate());
					}
				} else {
					// 插入 转账记录
					int c = transferLogDao.insertSelective(transferLog);
					if(c != 1) {
						log.warn("推荐人-{}-{}-{} 插入转账记录失败：{}", refSign, customerNumber, po.getStatisticsDate());
					}
				}
				// 更新佣金发放状态
				boolean bl = updateStatCommissionTransferStat(refSign, po.getStatisticsDate(), TransferStatus.suc);
				if(!bl) {
					log.warn("推荐人-{}-{}-{} 更新佣金发放状态失败：{}", refSign, customerNumber, po.getStatisticsDate());
				}
				
				retMap.put("isSuc", true);
				retMap.put("desc", "请求成功");
			} else {
				transferLog.setTransferStatus(TransferStatus.fail.getCode());
				String retMessage = ObjectUtils.toString(ret.get("message"), "");
				transferLog.setRemark(retMessage);
				
				log.info("推荐人-{}-{}-{} 发起转账请求失败：{}", refSign, customerNumber, po.getStatisticsDate(), retMessage);
				
				// 删除队列
				boolean delCanTransfer = delCanTransfer(refSign, po.getStatisticsDate());
				if(!delCanTransfer) {
					log.warn("推荐人-{}-{}-{} 删除 transfer que 结果：{}", refSign, customerNumber, po.getStatisticsDate(), delCanTransfer);
				}
				
				if(isFailover) {
					// 更新 转账记录
					NnTransferLogExample condition = new NnTransferLogExample();
					com.n.ysb.service.transfer.po.NnTransferLogExample.Criteria logCriteria = condition.createCriteria();
					logCriteria.andRefSignEqualTo(refSign);
					logCriteria.andTransferDateEqualTo(po.getStatisticsDate());
					int c = transferLogDao.updateByExampleSelective(transferLog, condition);
					if(c != 1) {
						log.warn("推荐人-{}-{}-{} 更新转账记录失败：{}", refSign, customerNumber, po.getStatisticsDate());
					}
				} else {
					// 插入 转账记录
					int c = transferLogDao.insertSelective(transferLog);
					if(c != 1) {
						log.warn("推荐人-{}-{}-{} 插入转账记录失败：{}", refSign, customerNumber, po.getStatisticsDate());
					}
				}
				// 更新佣金发放状态
				boolean bl = updateStatCommissionTransferStat(refSign, po.getStatisticsDate(), TransferStatus.fail);
				if(!bl) {
					log.warn("推荐人-{}-{}-{} 更新佣金发放状态失败：{}", refSign, customerNumber, po.getStatisticsDate());
				}
				
				retMap.put("isSuc", false);
				retMap.put("desc", retMessage);
			}
		} catch (Exception ex) {
			// do noting, but continue
			log.error("转账异常：", ex);
			retMap.put("isSuc", false);
			retMap.put("desc", "转账异常");
		}
		
		return retMap;
	}
	
	/**
	 * 转账失败后，重新发起
	 */
	public Map<String, Object> failover(NnStatistics po) {
		return this.singleTransfer(po, true);
	}
	
	/**
	 *  一个子商户一天只能转账一次
	 *  transferDate: 统计日起，即交易日起
	 */
	private boolean canTransfer(String refSign, Date transferDate) {
		NnTransferQue que = new NnTransferQue();
		que.setRefSign(refSign);
		que.setTransferDate(transferDate);
		try {
			int c = transferQueDao.insertSelective(que);
			return c == 1? true : false;
		} catch(Exception ex) {
			log.error("", ex);
			return false;
		}
	}
	
	private boolean delCanTransfer(String refSign, Date transferDate) {
		NnTransferQueExample condition = new NnTransferQueExample();
		Criteria criteria = condition.createCriteria();
		criteria.andRefSignEqualTo(refSign);
		criteria.andTransferDateEqualTo(transferDate);
		int c = transferQueDao.deleteByExample(condition);
		return c == 1? true : false;
	}
	
	private boolean updateStatCommissionTransferStat(String refSign, Date statisticsDate, TransferStatus status) {
		NnStatisticsExample condition = new NnStatisticsExample();
		com.n.ysb.service.statistics.po.NnStatisticsExample.Criteria criteria = condition.createCriteria();
		criteria.andStatisticsTypeEqualTo(refSign);
		criteria.andStatisticsDateEqualTo(statisticsDate);
		
		NnStatistics record = new NnStatistics();
		record.setCommissionTransferStat(status.getCode());
		int c = statDao.updateByExampleSelective(record, condition);
		return c == 1 ? true : false;
	}
	
}
