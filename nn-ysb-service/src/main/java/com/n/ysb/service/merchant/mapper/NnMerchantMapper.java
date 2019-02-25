package com.n.ysb.service.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;

public interface NnMerchantMapper {
    int countByExample(NnMerchantExample example);

    int deleteByExample(NnMerchantExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnMerchant record);

    int insertSelective(NnMerchant record);

    List<NnMerchant> selectByExample(NnMerchantExample example);

    NnMerchant selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnMerchant record, @Param("example") NnMerchantExample example);

    int updateByExample(@Param("record") NnMerchant record, @Param("example") NnMerchantExample example);

    int updateByPrimaryKeySelective(NnMerchant record);

    int updateByPrimaryKey(NnMerchant record);
}