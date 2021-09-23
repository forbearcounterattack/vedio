package com.fighting.store.user.dao;

import com.fighting.store.user.entity.User;
import com.fighting.store.user.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 分页查询用户
     * @return
     */
    List<User> selectUserByPage(String searchContent);
}