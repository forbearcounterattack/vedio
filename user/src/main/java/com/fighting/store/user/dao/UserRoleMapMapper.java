package com.fighting.store.user.dao;

import com.fighting.store.user.entity.UserRoleMap;
import com.fighting.store.user.entity.UserRoleMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapMapper {
    long countByExample(UserRoleMapExample example);

    int deleteByExample(UserRoleMapExample example);

    int insert(UserRoleMap record);

    int insertSelective(UserRoleMap record);

    List<UserRoleMap> selectByExample(UserRoleMapExample example);

    int updateByExampleSelective(@Param("record") UserRoleMap record, @Param("example") UserRoleMapExample example);

    int updateByExample(@Param("record") UserRoleMap record, @Param("example") UserRoleMapExample example);
}