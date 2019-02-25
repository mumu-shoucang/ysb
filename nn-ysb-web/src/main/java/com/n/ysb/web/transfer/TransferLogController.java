package com.n.ysb.web.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.transfer.impl.TransferLogService;
import com.n.ysb.service.transfer.po.NnTransferLog;
import com.n.ysb.service.transfer.vo.TransferLogVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("transferLog")
public class TransferLogController extends BaseController {

	@Autowired
	private TransferLogService transferLogService;
	
	@RequestMapping("listPage")
	@ResponseBody
	public SimpleResponse listPage(TransferLogVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      ) {
		PageInfo<NnTransferLog> transferLogs = transferLogService.listPage(vo, pageNo, limit);
		return SimpleResponse.suc(transferLogs);
	}
	
}
