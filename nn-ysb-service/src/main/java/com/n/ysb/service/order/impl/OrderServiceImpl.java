package com.n.ysb.service.order.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.business.log.OrderStatusLog;
import com.n.ysb.service.merchant.impl.MerchantServiceImpl;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.mapper.NnOrderMapper;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;
import com.n.ysb.service.order.po.NnOrderExample.Criteria;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.orderLog.mapper.NnOrderLogMapper;
import com.n.ysb.service.orderLog.po.NnOrderLog;
import com.n.ysb.service.thirdparty.WithdrawType;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class OrderServiceImpl implements IOrderService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NnOrderMapper orderMapper;
	@Autowired
	private NnOrderLogMapper orderLogMapper;
	
	@Autowired
	private MerchantServiceImpl merchantService;
	
	@Autowired
	private IYeePayRequestService yeePayRequestService;
	
	@Autowired
    private OrderStatusLog orderStatusLog;
	
	
	@Override
	public NnOrder getOrderDetails(String id) {
		NnOrder order = orderMapper.selectByPrimaryKey(id);
		log.info("====================订单详情查询-:{}:"+order);
		return order;
	}
	
	@Override
	public  List<NnOrderLog> getOrderLogDetails(String id) {
		NnOrder order = orderMapper.selectByPrimaryKey(id);
		List<NnOrderLog> orderLog = orderLogMapper.selectByOrderNo(order.getOrderNo());
		log.info("====================订单日志查询-:{}:"+orderLog);
		return orderLog;
	}	
	
	@Override
	public List<NnOrder> selectByExample(NnOrderVo vo) {
		NnOrderExample example = new NnOrderExample();
		Criteria criteria = example.createCriteria();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
    	
    	// 订单号
    	if(StringUtils.isNotBlank(vo.getOrderNo())){
			criteria.andOrderNoEqualTo(vo.getOrderNo());
		}
    	// 推荐人标识
    	if(StringUtils.isNotBlank(vo.getRefSign())){
			criteria.andRefSignEqualTo(vo.getRefSign());
		}
    	//订单金额
    	if(vo.getOrderAmt()!=null){
			criteria.andOrderAmtEqualTo(vo.getOrderAmt());
		}
    	//订单状态
    	if(StringUtils.isNotBlank(vo.getOrderStatus())){
    		criteria.andOrderStatusEqualTo(vo.getOrderStatus());
    	}
		//下单时间
		try {
			if(StringUtils.isNotBlank(vo.getStartTime())){
				startTime = sdf.parse(vo.getStartTime()+" 00:00:00");
				criteria.andCreateDateGreaterThanOrEqualTo(startTime);
			}
			if(StringUtils.isNotBlank(vo.getEndTime())){
				endTime = sdf.parse(vo.getEndTime()+" 23:59:59");
				criteria.andCreateDateLessThanOrEqualTo(endTime);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		example.setOrderByClause("ORDER_NO desc,CREATE_DATE desc");
		
		List<NnOrder> selectByExample = orderMapper.selectByExample(example);
		
		return selectByExample;
	}
	
	@Override
	public PageInfo<NnOrder> getOrderPage(NnOrderVo vo, int pageNo, int limit) {
		Page<NnOrder> startPage = PageHelper.startPage(pageNo, limit);
    	NnOrderExample example = new NnOrderExample();
    	Criteria criteria = example.createCriteria();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
    	
    	// 订单号
    	if(StringUtils.isNotBlank(vo.getOrderNo())){
			criteria.andOrderNoEqualTo(vo.getOrderNo());
		}
    	// 推荐人标识
    	if(StringUtils.isNotBlank(vo.getRefSign())){
			criteria.andRefSignEqualTo(vo.getRefSign());
		}
    	//订单金额
    	if(vo.getOrderAmt()!=null){
			criteria.andOrderAmtEqualTo(vo.getOrderAmt());
		}
    	//订单状态
    	if(StringUtils.isNotBlank(vo.getOrderStatus())){
    		criteria.andOrderStatusEqualTo(vo.getOrderStatus());
    	}
		//下单时间
		try {
			if(StringUtils.isNotBlank(vo.getStartTime())){
				startTime = sdf.parse(vo.getStartTime()+" 00:00:00");
				criteria.andCreateDateGreaterThanOrEqualTo(startTime);
			}
			if(StringUtils.isNotBlank(vo.getEndTime())){
				endTime = sdf.parse(vo.getEndTime()+" 23:59:59");
				criteria.andCreateDateLessThanOrEqualTo(endTime);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		example.setOrderByClause("ORDER_NO desc,CREATE_DATE desc");
		
		List<NnOrder> selectByExample = orderMapper.selectByExample(example);
		log.info("===========订单分页查询:" + selectByExample);
		return startPage.toPageInfo();
	}

    @Override
    public List<NnOrderVo> getUnSuccessOrderVo(){
        List<NnOrderVo> orderVoList = new ArrayList<NnOrderVo>();
        NnOrderExample example = new NnOrderExample();
        Criteria criteria1 = example.createCriteria();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
        Date before10MiDate = calendar.getTime();
        
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        Date before40MiDate = calendar.getTime();
        
        
        criteria1.andCreateDateGreaterThanOrEqualTo(before40MiDate);
        criteria1.andCreateDateLessThanOrEqualTo(before10MiDate);
        criteria1.andOrderStatusEqualTo(OrderStatus.init.getCode());
        
        Criteria criteria2 = example.or();
        criteria2.andCreateDateGreaterThanOrEqualTo(before40MiDate);
        criteria2.andCreateDateLessThanOrEqualTo(before10MiDate);
        criteria2.andOrderStatusEqualTo(OrderStatus.paying.getCode());
        
        
//        example.or(criteria2);
        List<NnOrder> orderList = orderMapper.selectByExample(example);
        for (NnOrder nnOrder : orderList) {
            NnOrderVo orderVo = new NnOrderVo();
            BeanUtils.copyProperties(nnOrder, orderVo);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
    
    @Override
    public boolean updateOrderVo(NnOrderVo orderVo){
        NnOrderExample example = new NnOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(orderVo.getId());
        criteria.andOrderStatusNotEqualTo(orderVo.getOrderStatus());
        
        NnOrder order = new NnOrder();
        BeanUtils.copyProperties(orderVo, order);
        
        int c = orderMapper.updateByExampleSelective(order, example);
        
        return c==1 ? true : false;
        
    }

    @Override
    public List<NnOrderVo> getUnWithDrawSuccessOrderVo() {
        List<NnOrderVo> orderVoList = new ArrayList<NnOrderVo>();
        NnOrderExample example = new NnOrderExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(OrderStatus.pay_suc.getCode());
        criteria.andYeeWithdrawStatusIsNull();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
        Date before10MiDate = calendar.getTime();
        
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 110);
        Date before120MiDate = calendar.getTime();
        
        criteria.andCreateDateGreaterThanOrEqualTo(before120MiDate);
        criteria.andCreateDateLessThanOrEqualTo(before10MiDate);
        
        List<NnOrder> orderList = orderMapper.selectByExample(example);
        for (NnOrder nnOrder : orderList) {
            NnOrderVo orderVo = new NnOrderVo();
            BeanUtils.copyProperties(nnOrder, orderVo);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
    
    private NnOrder getOrderById(String id){
        NnOrder order = orderMapper.selectByPrimaryKey(id);
        return order;
    }
    
    @Override
    public void withDraw(String id) {
        NnOrder orderById = this.getOrderById(id);
        NnMerchantVo merchantVo = merchantService.getMerchantByLoginMobile(orderById.getMerchantMobile());
        
        BigDecimal orderAmt = orderById.getOrderAmt();
        BigDecimal yeeTradeFee = orderById.getYeeTradeFee();
        
        BigDecimal withDrawAmt = orderAmt.subtract(yeeTradeFee);
        
        WithDrawVo withDrawVo = new WithDrawVo();
        withDrawVo.setWithDrawAmt(withDrawAmt);
        withDrawVo.setExternalNo(orderById.getOrderNo());
        
        Map<String, Object> withDraw = yeePayRequestService.withDraw(merchantVo, withDrawVo, WithdrawType.order_hand_withdraw.getCode());
        
        orderStatusLog.logOrderPaySuc(orderById.getOrderNo(), "发起结算请求，返回json:"+withDraw);
        
    }
    
    @Override
    public List<NnOrderVo> getFeeStatiticsOrder(){
        List<NnOrderVo> orderVoList = new ArrayList<NnOrderVo>();
        NnOrderExample example = new NnOrderExample();
        Criteria criteria = example.createCriteria();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timsStr = format.format(time);
        
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = format2.parse(timsStr+ " 00:00:00");
            endTime = format2.parse(timsStr+ " 23:59:59");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        criteria.andYeeWithdrawHandleDateGreaterThanOrEqualTo(startTime);
        criteria.andYeeWithdrawHandleDateLessThanOrEqualTo(endTime);
        criteria.andOrderStatusEqualTo(OrderStatus.withdraw_suc.getCode());
        
        List<NnOrder> orderList = orderMapper.selectByExample(example);
        
        for (NnOrder nnOrder : orderList) {
            NnOrderVo orderVo = new NnOrderVo();
            BeanUtils.copyProperties(nnOrder, orderVo);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    @Override
    public List<NnOrder> getOrderByRefSign(String refSign) {
        List<NnOrderVo> orderVoList = new ArrayList<NnOrderVo>();
        NnOrderExample example = new NnOrderExample();
        Criteria criteria = example.createCriteria();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timsStr = format.format(time);
        
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = format2.parse(timsStr+ " 00:00:00");
            endTime = format2.parse(timsStr+ " 23:59:59");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO 添加统计条件
        criteria.andYeeWithdrawHandleDateGreaterThanOrEqualTo(startTime);
        criteria.andYeeWithdrawHandleDateLessThanOrEqualTo(endTime);
        criteria.andRefSignEqualTo(refSign);
        criteria.andOrderStatusEqualTo(OrderStatus.withdraw_suc.getCode());
        
        List<NnOrder> orderList = orderMapper.selectByExample(example);
        return orderList;
    }
}
