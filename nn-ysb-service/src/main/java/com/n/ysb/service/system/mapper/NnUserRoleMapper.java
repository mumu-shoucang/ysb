package com.n.ysb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.system.po.NnUserRole;
import com.n.ysb.service.system.po.NnUserRoleExample;

public interface NnUserRoleMapper {
    int countByExample(NnUserRoleExample example);

    int deleteByExample(NnUserRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnUserRole record);

    int insertSelective(NnUserRole record);

    List<NnUserRole> selectByExample(NnUserRoleExample example);

    NnUserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnUserRole record, @Param("example") NnUserRoleExample example);

    int updateByExample(@Param("record") NnUserRole record, @Param("example") NnUserRoleExample example);

    int updateByPrimaryKeySelective(NnUserRole record);

    int updateByPrimaryKey(NnUserRole record);
    
    int insertUserRoles(List<NnUserRole> pos);
}