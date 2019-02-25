package com.n.ysb.service.statistics.schedule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.statistics.StatisticsType;
import com.n.ysb.service.statistics.mapper.NnStatisticsMapper;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.vo.NnStatisticsVo;

@Service
public class MainCustomerStatistics {
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private NnStatisticsMapper statisticsMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public void mainCustomerStatistics() throws ParseException{
        logger.info("系统商费率统计开始");
        List<NnOrderVo> statiticsOrder = orderService.getFeeStatiticsOrder();
        
        BigDecimal feeStatisticsAmt = new BigDecimal(0);
        BigDecimal refStatisticsAmt = new BigDecimal(0);
        for (NnOrderVo orderVo : statiticsOrder) {
            feeStatisticsAmt = feeStatisticsAmt.add(orderVo.getYeeTradeFee()).add(orderVo.getYeeT0WithdrawFee()).add(orderVo.getYeeT0WithdrawExfee());
            refStatisticsAmt = refStatisticsAmt.add(orderVo.getCommisstion());
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        Date parse = format.parse(format.format(time));
        
        NnStatistics feeStatistics = new NnStatistics();
        feeStatistics.setStatisticsDate(parse);
        feeStatistics.setStatisticsType(StatisticsType.MainCustomer.getCode());
        feeStatistics.setStatisticsAmt(feeStatisticsAmt.setScale(6,BigDecimal.ROUND_HALF_UP));
        
        statisticsMapper.insertSelective(feeStatistics);
        
//        NnStatistics refStatistics = new NnStatistics();
//        refStatistics.setStatisticsDate(parse);
//        refStatistics.setStatisticsType(StatisticsType.Commisstion.getCode());
//        refStatistics.setStatisticsAmt(refStatisticsAmt.setScale(6,BigDecimal.ROUND_DOWN));
//        
//        statisticsMapper.insertSelective(refStatistics);
    }
    
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String format3 = format.format(time);
        
        Date parse = format2.parse(format3+" 23:59:59");
        System.out.println(parse);
    }
}
