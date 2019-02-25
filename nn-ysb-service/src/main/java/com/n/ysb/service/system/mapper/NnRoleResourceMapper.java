package com.n.ysb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.system.po.NnRoleResource;
import com.n.ysb.service.system.po.NnRoleResourceExample;

public interface NnRoleResourceMapper {
    int countByExample(NnRoleResourceExample example);

    int deleteByExample(NnRoleResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnRoleResource record);

    int insertSelective(NnRoleResource record);

    List<NnRoleResource> selectByExample(NnRoleResourceExample example);

    NnRoleResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnRoleResource record, @Param("example") NnRoleResourceExample example);

    int updateByExample(@Param("record") NnRoleResource record, @Param("example") NnRoleResourceExample example);

    int updateByPrimaryKeySelective(NnRoleResource record);

    int updateByPrimaryKey(NnRoleResource record);
    
    int insertRoleRess(List<NnRoleResource> pos);
}