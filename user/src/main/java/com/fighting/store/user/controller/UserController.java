package com.fighting.store.user.controller;

import com.fighting.store.user.common.constants.ErrorEnum;
import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.dto.UserDto;
import com.fighting.store.user.entity.User;
import com.fighting.store.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理类")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value="添加用户", notes="添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "gender", value = "性别 1男 2女", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "userStatus", value = "用户状态 1正常 2冻结", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "addr", value = "地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "picture", value = "头像", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userDesc", value = "用户描述", required = false, dataType = "String")
    })
    @PostMapping("add")
    public Result add(@RequestBody UserDto userDto){
        Result result = userService.addUser(userDto);
        logger.info("结果："+result.getMsg());
        return result;
    }

    @ApiOperation(value="更新用户", notes="更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "email", value = "邮箱", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "gender", value = "性别 1男 2女", required = false, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "userStatus", value = "用户状态 1正常 2冻结", required = false, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "addr", value = "地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "picture", value = "头像", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userDesc", value = "用户描述", required = false, dataType = "String")
    })
    @PostMapping("update")
    public Result update(@RequestBody UserDto userDto){
        Result result = userService.updateUser(userDto);
        logger.info("结果："+result.getMsg());
        return result;
    }

    @ApiOperation(value="查询用户", notes="查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true, dataType = "Integer")
    })
    @GetMapping("get")
    public Result get(@RequestParam Integer userId){
        Result result = userService.getUser(userId);
        logger.info("结果："+result.getMsg());
        return result;
    }

    @ApiOperation(value="查询用户列表", notes="查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "第几页", required = false, defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页数量", required = false, defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "searchContent", value = "查询关键词", required = false, defaultValue = "", dataType = "String")
    })
    @GetMapping("getList")
    public Result getList(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String searchContent){
        List<User> userList= userService.getUserList(pageNum,pageSize,searchContent);
        Result result = new Result();
        result.setCode(ErrorEnum.SUCCESS.getErrorCode());
        result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
        result.setSuccess(true);
        PageInfo<User> resultList = new PageInfo<User>(userList);
        result.setData(resultList);
        logger.info("结果："+result.getMsg());
        return result;
    }

    @ApiOperation(value="删除用户", notes="删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @PostMapping("delete")
    public Result delete(@RequestBody UserDto userDto){
        Result result = userService.deleteUser(userDto);
        logger.info("结果："+result.getMsg());
        return result;
    }
}
