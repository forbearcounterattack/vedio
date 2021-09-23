package com.fighting.store.user.controller;

import com.fighting.store.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
@Api(value = "测试类")
public class TestController {

    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String name(){
        return name;
    }

    @Autowired
    private UserService userService;

    @ApiOperation(value="修改用户密码", notes="根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @GetMapping("modifyPassword")
    public String modifyPassword(){
        return "success";
    }

    @GetMapping("allUserCount")
    public Integer allUserCount(){
        Integer userCount = this.userService.getAllUserCount();
        return userCount;
    }


    @GetMapping("time")
    public String test(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String result = simpleDateFormat.format(date);
        return result;
    }
}
