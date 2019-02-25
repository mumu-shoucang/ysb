package com.n.ysb.service.withdraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample;

public interface NnYeecustomerWithdrawMapper {
    int countByExample(NnYeecustomerWithdrawExample example);

    int deleteByExample(NnYeecustomerWithdrawExample example);

    int insert(NnYeecustomerWithdraw record);

    int insertSelective(NnYeecustomerWithdraw record);

    List<NnYeecustomerWithdraw> selectByExample(NnYeecustomerWithdrawExample example);

    int updateByExampleSelective(@Param("record") NnYeecustomerWithdraw record, @Param("example") NnYeecustomerWithdrawExample example);

    int updateByExample(@Param("record") NnYeecustomerWithdraw record, @Param("example") NnYeecustomerWithdrawExample example);
}