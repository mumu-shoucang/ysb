package com.n.ysb.service.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.po.NnStatisticsExample;

public interface NnStatisticsMapper {
    int countByExample(NnStatisticsExample example);

    int deleteByExample(NnStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnStatistics record);

    int insertSelective(NnStatistics record);

    List<NnStatistics> selectByExample(NnStatisticsExample example);

    NnStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnStatistics record, @Param("example") NnStatisticsExample example);

    int updateByExample(@Param("record") NnStatistics record, @Param("example") NnStatisticsExample example);

    int updateByPrimaryKeySelective(NnStatistics record);

    int updateByPrimaryKey(NnStatistics record);
}