package com.n.ysb.service.calendar.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.n.ysb.service.calendar.po.NnCalendar;
import com.n.ysb.service.calendar.po.NnCalendarExample;

public interface NnCalendarMapper {
    int countByExample(NnCalendarExample example);

    int deleteByExample(NnCalendarExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnCalendar record);

    int insertSelective(NnCalendar record);

    List<NnCalendar> selectByExample(NnCalendarExample example);

    NnCalendar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnCalendar record, @Param("example") NnCalendarExample example);

    int updateByExample(@Param("record") NnCalendar record, @Param("example") NnCalendarExample example);

    int updateByPrimaryKeySelective(NnCalendar record);

    int updateByPrimaryKey(NnCalendar record);
}