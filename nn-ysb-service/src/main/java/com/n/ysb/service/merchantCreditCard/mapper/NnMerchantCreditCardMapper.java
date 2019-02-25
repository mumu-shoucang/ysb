package com.n.ysb.service.merchantCreditCard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCard;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample;

public interface NnMerchantCreditCardMapper {
    int countByExample(NnMerchantCreditCardExample example);

    int deleteByExample(NnMerchantCreditCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnMerchantCreditCard record);

    int insertSelective(NnMerchantCreditCard record);

    List<NnMerchantCreditCard> selectByExample(NnMerchantCreditCardExample example);

    NnMerchantCreditCard selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") NnMerchantCreditCard record, @Param("example") NnMerchantCreditCardExample example);

    int updateByExample(@Param("record") NnMerchantCreditCard record, @Param("example") NnMerchantCreditCardExample example);

    int updateByPrimaryKeySelective(NnMerchantCreditCard record);

    int updateByPrimaryKey(NnMerchantCreditCard record);
}