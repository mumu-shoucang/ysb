package com.n.ysb.service.merchantLog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.merchantLog.po.NnMerchantLog;
import com.n.ysb.service.merchantLog.po.NnMerchantLogExample;

public interface NnMerchantLogMapper {
    int countByExample(NnMerchantLogExample example);

    int deleteByExample(NnMerchantLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnMerchantLog record);

    int insertSelective(NnMerchantLog record);

    List<NnMerchantLog> selectByExample(NnMerchantLogExample example);

    NnMerchantLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnMerchantLog record, @Param("example") NnMerchantLogExample example);

    int updateByExample(@Param("record") NnMerchantLog record, @Param("example") NnMerchantLogExample example);

    int updateByPrimaryKeySelective(NnMerchantLog record);

    int updateByPrimaryKey(NnMerchantLog record);
}