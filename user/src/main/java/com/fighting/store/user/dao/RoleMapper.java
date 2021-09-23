package com.fighting.store.user.dao;

import com.fighting.store.user.entity.Role;
import com.fighting.store.user.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);
}