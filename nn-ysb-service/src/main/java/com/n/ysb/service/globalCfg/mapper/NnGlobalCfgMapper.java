package com.n.ysb.service.globalCfg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.globalCfg.po.NnGlobalCfg;
import com.n.ysb.service.globalCfg.po.NnGlobalCfgExample;

public interface NnGlobalCfgMapper {
    int countByExample(NnGlobalCfgExample example);

    int deleteByExample(NnGlobalCfgExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnGlobalCfg record);

    int insertSelective(NnGlobalCfg record);

    List<NnGlobalCfg> selectByExample(NnGlobalCfgExample example);

    NnGlobalCfg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnGlobalCfg record, @Param("example") NnGlobalCfgExample example);

    int updateByExample(@Param("record") NnGlobalCfg record, @Param("example") NnGlobalCfgExample example);

    int updateByPrimaryKeySelective(NnGlobalCfg record);

    int updateByPrimaryKey(NnGlobalCfg record);
}