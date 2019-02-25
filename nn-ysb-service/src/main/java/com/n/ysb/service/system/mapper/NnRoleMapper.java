package com.n.ysb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.system.po.NnRole;
import com.n.ysb.service.system.po.NnRoleExample;

public interface NnRoleMapper {
    int countByExample(NnRoleExample example);

    int deleteByExample(NnRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnRole record);

    int insertSelective(NnRole record);

    List<NnRole> selectByExample(NnRoleExample example);

    NnRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnRole record, @Param("example") NnRoleExample example);

    int updateByExample(@Param("record") NnRole record, @Param("example") NnRoleExample example);

    int updateByPrimaryKeySelective(NnRole record);

    int updateByPrimaryKey(NnRole record);
}