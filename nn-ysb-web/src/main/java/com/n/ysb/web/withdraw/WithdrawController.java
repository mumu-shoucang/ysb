package com.n.ysb.web.withdraw;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.withdraw.impl.WithdrawLogService;
import com.n.ysb.service.withdraw.impl.WithdrawService;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;
import com.n.ysb.service.withdraw.vo.NnYeecustomerWithdrawVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {
    
    @Autowired
    private WithdrawService withdrawService;
    
    @Autowired
    private WithdrawLogService withdrawLogService;
    

    @RequestMapping("getOrderPage")
    @ResponseBody
    public SimpleResponse getOrderPage(NnYeecustomerWithdrawVo vo, 
          @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
          )throws ParseException{
        PageInfo<NnYeecustomerWithdraw> orderPage = withdrawService.getOrderPage(vo, pageNo, limit);
        return SimpleResponse.suc(orderPage);
    }
    
    @RequestMapping("getWithdrawOrderByOid")
    @ResponseBody
    public SimpleResponse getWithdrawOrderByOid(String oid){
        NnYeecustomerWithdraw withdrawOrderByOid = withdrawService.getWithdrawOrderByOid(oid);
        return SimpleResponse.suc(withdrawOrderByOid);
    }
    
    @RequestMapping("getWithdrawLog")
    @ResponseBody
    public SimpleResponse getWithdrawLog(String orderNo){
        List<NnYeecustomerWithdrawLog> withdrawLogList = withdrawLogService.getWithdrawLog(orderNo);
        return SimpleResponse.suc(withdrawLogList);
    }

}
