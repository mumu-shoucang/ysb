package com.n.ysb.service.statistics;

import org.apache.poi.ss.usermodel.Workbook;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.vo.NnStatisticsVo;

public interface IStatisticsService {

    PageInfo<NnStatistics> getStatisticsByType(NnStatisticsVo statisticsVo, int pageNo, int limit);
    
    PageInfo<NnStatistics> getUntransferStatistics(NnStatisticsVo statisticsVo, int pageNo, int limit);

    Workbook export(NnStatisticsVo statisticsVo);

}
