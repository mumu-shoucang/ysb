package com.n.ysb.web.transfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.transfer.impl.TransferService;
import com.n.ysb.service.transfer.vo.TransferLogVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("transfer")
public class TransferController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private TransferService transferService;
	
	@RequestMapping("batchTransfer")
	@ResponseBody
	public SimpleResponse batchTransfer(@RequestBody BatchTransferVo batchTransfer) {
		// check smsCode
		if(!transferService.checkSmsCode(batchTransfer.getSmsCode())) {
			Map<String, Object> descMap = new HashMap<>();
			descMap.put("smsError", "短信验证码非法");
			return SimpleResponse.suc(descMap);
		}
		
		List<NnStatistics> statPos = batchTransfer.getStatPos();
		log.info("推荐人批量佣金结算接口接收到待结的数据条数：{}", statPos.size());
		Map<String, Object> ret = transferService.batchTransfer(statPos);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("singleTransfer")
	@ResponseBody
	public SimpleResponse singleTransfer(TransferLogVo log) {
		NnStatistics po = new NnStatistics();
		po.setStatisticsDate(log.getTransferDate());
		po.setStatisticsType(log.getRefSign());
		po.setTradeCommisstion(log.getTransferAmt());
		po.setYeeCustomerNumber(log.getYeeCustomerNumber());
		Map<String, Object> ret = transferService.singleTransfer(po, false);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("failover")
	@ResponseBody
	public SimpleResponse failover(TransferLogVo log) {
		NnStatistics po = new NnStatistics();
		po.setStatisticsDate(log.getTransferDate());
		po.setStatisticsType(log.getRefSign());
		po.setTradeCommisstion(log.getTransferAmt());
		po.setYeeCustomerNumber(log.getYeeCustomerNumber());
		Map<String, Object> ret = transferService.failover(po);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("sendSMS")
	@ResponseBody
	public SimpleResponse sendSMS(String msg) {
		Map<String, Object> ret = transferService.sendSMSCode(msg);
		return SimpleResponse.suc(ret);
	}

}
