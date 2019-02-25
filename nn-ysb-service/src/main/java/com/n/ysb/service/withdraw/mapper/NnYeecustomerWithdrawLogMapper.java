package com.n.ysb.service.withdraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLogExample;

public interface NnYeecustomerWithdrawLogMapper {
    int countByExample(NnYeecustomerWithdrawLogExample example);

    int deleteByExample(NnYeecustomerWithdrawLogExample example);

    int insert(NnYeecustomerWithdrawLog record);

    int insertSelective(NnYeecustomerWithdrawLog record);

    List<NnYeecustomerWithdrawLog> selectByExample(NnYeecustomerWithdrawLogExample example);

    int updateByExampleSelective(@Param("record") NnYeecustomerWithdrawLog record, @Param("example") NnYeecustomerWithdrawLogExample example);

    int updateByExample(@Param("record") NnYeecustomerWithdrawLog record, @Param("example") NnYeecustomerWithdrawLogExample example);
}