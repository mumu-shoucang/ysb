package com.n.ysb.service.transfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.transfer.po.NnTransferQue;
import com.n.ysb.service.transfer.po.NnTransferQueExample;

public interface NnTransferQueMapper {
    int countByExample(NnTransferQueExample example);

    int deleteByExample(NnTransferQueExample example);

    int insert(NnTransferQue record);

    int insertSelective(NnTransferQue record);

    List<NnTransferQue> selectByExample(NnTransferQueExample example);

    int updateByExampleSelective(@Param("record") NnTransferQue record, @Param("example") NnTransferQueExample example);

    int updateByExample(@Param("record") NnTransferQue record, @Param("example") NnTransferQueExample example);
}