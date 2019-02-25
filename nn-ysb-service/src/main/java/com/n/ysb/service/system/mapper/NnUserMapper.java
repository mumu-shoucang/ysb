package com.n.ysb.service.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.system.po.NnUser;
import com.n.ysb.service.system.po.NnUserExample;

public interface NnUserMapper {
    int countByExample(NnUserExample example);

    int deleteByExample(NnUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnUser record);

    int insertSelective(NnUser record);

    List<NnUser> selectByExample(NnUserExample example);

    NnUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnUser record, @Param("example") NnUserExample example);

    int updateByExample(@Param("record") NnUser record, @Param("example") NnUserExample example);

    int updateByPrimaryKeySelective(NnUser record);

    int updateByPrimaryKey(NnUser record);
    
    Set<String> findRoles(String loginName);
    
    Set<String> findPermissions(String loginName);
    
}