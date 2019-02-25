package com.n.ysb.service.statistics.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.statistics.IStatisticsService;
import com.n.ysb.service.statistics.StatisticsType;
import com.n.ysb.service.statistics.mapper.NnStatisticsMapper;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.po.NnStatisticsExample;
import com.n.ysb.service.statistics.po.NnStatisticsExample.Criteria;
import com.n.ysb.service.statistics.vo.NnStatisticsVo;
import com.n.ysb.service.system.CUser;
import com.n.ysb.service.transfer.impl.TransferStatus;

@Service
public class StatisticsServiceImpl implements IStatisticsService {
    
    @Autowired
    private NnStatisticsMapper statisticsMapper;
    
    @Autowired
    private CUser cUser;

    
    @Override
    public PageInfo<NnStatistics> getUntransferStatistics(NnStatisticsVo statisticsVo, int pageNo, int limit) {
        Page<NnStatistics> startPage = PageHelper.startPage(pageNo, limit);
        
        NnStatisticsExample example = new NnStatisticsExample();
        Criteria criteria = example.createCriteria();
        
		// 推荐人佣金: 需要有易宝的子商户编码，并且金额 >=0.01才可以结算
	    criteria.andStatisticsTypeNotEqualTo(StatisticsType.MainCustomer.getCode());
	    criteria.andYeeCustomerNumberIsNotNull();
	    criteria.andTradeCommisstionGreaterThanOrEqualTo(new BigDecimal("0.01"));
	    criteria.andCommissionTransferStatEqualTo(TransferStatus.untransfer.getCode());
	    
	    if(StringUtils.isNotBlank(statisticsVo.getStatisticsType())){
	        criteria.andStatisticsTypeEqualTo(statisticsVo.getStatisticsType());
	    }
	    
	    if(StringUtils.isNotBlank(statisticsVo.getExRefSign())){
	        criteria.andStatisticsTypeNotEqualTo(statisticsVo.getExRefSign());
	    }
	    
        if(statisticsVo.getStatisticsDate() != null){
            Date statisticsDate = statisticsVo.getStatisticsDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(statisticsDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andStatisticsDateEqualTo(parse);
        }
        if(statisticsVo.getStartDate()!=null){
            Date startDate = statisticsVo.getStartDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(startDate)+" 00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andStatisticsDateGreaterThanOrEqualTo(parse);
        }
        if(statisticsVo.getEndDate()!=null){
            Date endDate = statisticsVo.getEndDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(endDate)+" 23:59:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andStatisticsDateLessThanOrEqualTo(parse);
        }
        
        example.setOrderByClause("STATISTICS_DATE　DESC");
        List<NnStatistics> selectByExample = statisticsMapper.selectByExample(example);
        return startPage.toPageInfo();
    }
    
    @Override
    public PageInfo<NnStatistics> getStatisticsByType(NnStatisticsVo statisticsVo, int pageNo, int limit) {
        Page<NnStatistics> startPage = PageHelper.startPage(pageNo, limit);
        
        NnStatisticsExample example = new NnStatisticsExample();
        Criteria criteria = example.createCriteria();
        
        String cuserRefSign = cUser.cuserRefSign();
        
        // 系统商费率 统计
        if(StatisticsType.MainCustomer.getCode().equals(statisticsVo.getStatisticsType())){
            criteria.andStatisticsTypeEqualTo(StatisticsType.MainCustomer.getCode());
        }else{
        		// 推荐人佣金 统计
            criteria.andStatisticsTypeNotEqualTo(StatisticsType.MainCustomer.getCode());
            if(StringUtils.isNotBlank(statisticsVo.getStatisticsType())){
                criteria.andStatisticsTypeEqualTo(statisticsVo.getStatisticsType());
            }
            
            if(StringUtils.isNotBlank(cuserRefSign)){
                criteria.andStatisticsTypeEqualTo(cuserRefSign);
            }
            
            if(StringUtils.isNotBlank(statisticsVo.getExRefSign())){
                criteria.andStatisticsTypeNotEqualTo(statisticsVo.getExRefSign());
            }
            
            if(StringUtils.isNotBlank(statisticsVo.getCommissionTransferStat())){
                criteria.andCommissionTransferStatEqualTo(statisticsVo.getCommissionTransferStat());
            }
            
        }
        
        if(statisticsVo.getStatisticsDate() != null){
            Date statisticsDate = statisticsVo.getStatisticsDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(statisticsDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            criteria.andStatisticsDateEqualTo(parse);
        }
        
        if(statisticsVo.getStartDate()!=null){
            Date startDate = statisticsVo.getStartDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(startDate)+" 00:00:00");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            criteria.andStatisticsDateGreaterThanOrEqualTo(parse);
        }
        
        if(statisticsVo.getEndDate()!=null){
            Date endDate = statisticsVo.getEndDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(endDate)+" 23:59:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andStatisticsDateLessThanOrEqualTo(parse);
        }
        
        example.setOrderByClause("STATISTICS_DATE　DESC");
        
        List<NnStatistics> selectByExample = statisticsMapper.selectByExample(example);
        
        return startPage.toPageInfo();
    }

    @Override
    public Workbook export(NnStatisticsVo statisticsVo) {
        List<NnStatistics> list = this.getStatistics(statisticsVo);
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("佣金统计");
        String[] title = { "序号", "推荐人标识", "日期", "成功交易笔数", "总交易金额", "交易佣金比例", "交易佣金", "结算佣金", "注册用户数", "认证用户数", "转账状态"};
        int[] width = { 5 * 256, 22 * 256, 12 * 256, 15 * 256, 15 * 256, 15 * 256, 10 * 256, 10 * 256, 12 * 256, 12 * 256, 12 * 256};
        Row row3 = sheet.createRow(0);
        
        for (int i = 0; i < title.length; i++) {
            Cell cell = row3.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, width[i]);
        }
        
        //生成数据
        if(list != null){
            for (int i = 0; i < list.size(); i++) {
                NnStatistics statistics = list.get(i);
                Row row5 = sheet.createRow(i+1);
                for (int j = 0; j < title.length; j++) {
                    Cell cell = row5.createCell(j);
                    String titleTemp = title[j];
                    
                    if("序号".equals(titleTemp)){
                        cell.setCellValue(i + 1);
                    }
                    
                    if("推荐人标识".equals(titleTemp)){
                        cell.setCellValue(statistics.getStatisticsType());
                    }
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if("日期".equals(titleTemp)){
                        cell.setCellValue(sdf.format(statistics.getStatisticsDate()));
                    }
                    
                    if("成功交易笔数".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getTradeSucCount()));
                    }
                    
                    if("总交易金额".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getStatisticsAmt()));
                    }
                    
                    if("交易佣金比例".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getTradeCommisstionRate()));
                    }
                    
                    if("交易佣金".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getTradeCommisstion()));
                    }
                    
                    if("结算佣金".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getWithdrawCommisstion()));
                    }
                    
                    if("注册用户数".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getRegUserCount()));
                    }
                    
                    if("认证用户数".equals(titleTemp)){
                        cell.setCellValue(isNull(statistics.getAntuUserCount()));
                    }
                    
                    if("转账状态".equals(titleTemp)){
//                        cell.setCellValue(isNull(statistics.getAntuUserCount()));
                        cell.setCellValue(isNull(TransferStatus.getDescByCode(statistics.getCommissionTransferStat())));
                    }
                }
            }
        }
        
        
        return workbook;
    }
    
    private String isNull(Object object){
        if(object != null && object != "null"){
            return object+"";
        }else{
            return "0";
        }
    }
    
    private List<NnStatistics> getStatistics(NnStatisticsVo statisticsVo){
        NnStatisticsExample example = new NnStatisticsExample();
        Criteria criteria = example.createCriteria();
        
        if(StatisticsType.MainCustomer.getCode().equals(statisticsVo.getStatisticsType())){
            criteria.andStatisticsTypeEqualTo(StatisticsType.MainCustomer.getCode());
        }else{
            criteria.andStatisticsTypeNotEqualTo(StatisticsType.MainCustomer.getCode());
            if(StringUtils.isNotBlank(statisticsVo.getStatisticsType())){
                criteria.andStatisticsTypeEqualTo(statisticsVo.getStatisticsType());
            }
        }
        
        if(statisticsVo.getStatisticsDate() != null){
            Date statisticsDate = statisticsVo.getStatisticsDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(statisticsDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            criteria.andStatisticsDateEqualTo(parse);
        }
        
        if(statisticsVo.getStartDate()!=null){
            Date startDate = statisticsVo.getStartDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(startDate)+" 00:00:00");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            criteria.andStatisticsDateGreaterThanOrEqualTo(parse);
        }
        
        if(statisticsVo.getEndDate()!=null){
            Date endDate = statisticsVo.getEndDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = format.parse(format.format(endDate)+" 23:59:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andStatisticsDateLessThanOrEqualTo(parse);
        }
        
        if(StringUtils.isNotBlank(statisticsVo.getCommissionTransferStat())){
            criteria.andCommissionTransferStatEqualTo(statisticsVo.getCommissionTransferStat());
        }
        
        example.setOrderByClause("STATISTICS_DATE　DESC");
        
        List<NnStatistics> selectByExample = statisticsMapper.selectByExample(example);
        
        return selectByExample;
    }

}
