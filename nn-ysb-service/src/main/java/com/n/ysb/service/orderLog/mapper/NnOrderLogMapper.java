package com.n.ysb.service.orderLog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.orderLog.po.NnOrderLog;
import com.n.ysb.service.orderLog.po.NnOrderLogExample;

public interface NnOrderLogMapper {
    int countByExample(NnOrderLogExample example);

    int deleteByExample(NnOrderLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnOrderLog record);

    int insertSelective(NnOrderLog record);

    List<NnOrderLog> selectByExample(NnOrderLogExample example);

    NnOrderLog selectByPrimaryKey(String id);
    
    List<NnOrderLog> selectByOrderNo(String orderNo);

    int updateByExampleSelective(@Param("record") NnOrderLog record, @Param("example") NnOrderLogExample example);

    int updateByExample(@Param("record") NnOrderLog record, @Param("example") NnOrderLogExample example);

    int updateByPrimaryKeySelective(NnOrderLog record);

    int updateByPrimaryKey(NnOrderLog record);
}