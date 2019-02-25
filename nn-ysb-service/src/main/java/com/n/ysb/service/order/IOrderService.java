package com.n.ysb.service.order;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.orderLog.po.NnOrderLog;

public interface IOrderService {
	
	PageInfo<NnOrder> getOrderPage(NnOrderVo vo,int pageNo, int limit);
	
	NnOrder getOrderDetails (String id);

    List<NnOrderVo> getUnSuccessOrderVo();

    boolean updateOrderVo(NnOrderVo orderVo);
    
    List<NnOrderLog> getOrderLogDetails (String id);

    List<NnOrderVo> getUnWithDrawSuccessOrderVo();

    void withDraw(String id);

    List<NnOrder> getOrderByRefSign(String refSign);

    List<NnOrderVo> getFeeStatiticsOrder();
    
    List<NnOrder> selectByExample(NnOrderVo orderVo);

}
