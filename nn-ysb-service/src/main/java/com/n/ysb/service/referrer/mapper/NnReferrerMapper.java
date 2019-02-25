package com.n.ysb.service.referrer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;

public interface NnReferrerMapper {
    int countByExample(NnReferrerExample example);

    int deleteByExample(NnReferrerExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnReferrer record);

    int insertSelective(NnReferrer record);

    List<NnReferrer> selectByExample(NnReferrerExample example);
    
    List<NnReferrer> getByRefCode(@Param("refCode")String refCode);

    NnReferrer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnReferrer record, @Param("example") NnReferrerExample example);

    int updateByExample(@Param("record") NnReferrer record, @Param("example") NnReferrerExample example);

    int updateByPrimaryKeySelective(NnReferrer record);

    int updateByPrimaryKey(NnReferrer record);
}