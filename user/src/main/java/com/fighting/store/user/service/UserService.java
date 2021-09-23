package com.fighting.store.user.service;

import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.dto.UserDto;
import com.fighting.store.user.entity.User;

import java.util.List;

public interface UserService {

    Integer getAllUserCount();

    Integer addUser(User user);

    Result addUser(UserDto userDto);

    Result updateUser(UserDto userDto);

    Result deleteUser(UserDto userDto);

    Result getUser(Integer userId);

    List<User> getUserList(Integer pageNum,Integer pageSize,String searchContent);
}
