package com.n.ysb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.n.ysb.service.system.po.NnResource;
import com.n.ysb.service.system.po.NnResourceExample;

public interface NnResourceMapper {
    int countByExample(NnResourceExample example);

    int deleteByExample(NnResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(NnResource record);

    int insertSelective(NnResource record);

    List<NnResource> selectByExample(NnResourceExample example);

    NnResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NnResource record, @Param("example") NnResourceExample example);

    int updateByExample(@Param("record") NnResource record, @Param("example") NnResourceExample example);

    int updateByPrimaryKeySelective(NnResource record);

    int updateByPrimaryKey(NnResource record);
    
    List<NnResource> getMenuByLoginName(String loginName);
    
    int enableModule(String resCode);
    
    int disableModule(String resCode);
}