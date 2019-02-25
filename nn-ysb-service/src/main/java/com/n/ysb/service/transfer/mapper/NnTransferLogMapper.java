package com.n.ysb.service.transfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.transfer.po.NnTransferLog;
import com.n.ysb.service.transfer.po.NnTransferLogExample;

public interface NnTransferLogMapper {
    int countByExample(NnTransferLogExample example);

    int deleteByExample(NnTransferLogExample example);

    int insert(NnTransferLog record);

    int insertSelective(NnTransferLog record);

    List<NnTransferLog> selectByExample(NnTransferLogExample example);

    int updateByExampleSelective(@Param("record") NnTransferLog record, @Param("example") NnTransferLogExample example);

    int updateByExample(@Param("record") NnTransferLog record, @Param("example") NnTransferLogExample example);
}