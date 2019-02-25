package com.n.ysb.service.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;

public interface NnOrderMapper {
    int countByExample(NnOrderExample example);

    int deleteByExample(NnOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnOrder record);

    int insertSelective(NnOrder record);

    List<NnOrder> selectByExample(NnOrderExample example);

    NnOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnOrder record, @Param("example") NnOrderExample example);

    int updateByExample(@Param("record") NnOrder record, @Param("example") NnOrderExample example);

    int updateByPrimaryKeySelective(NnOrder record);

    int updateByPrimaryKey(NnOrder record);
}