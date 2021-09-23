package com.fighting.store.user.controller;

import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.dto.UserRoleMapDto;
import com.fighting.store.user.service.UserRoleMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/userRoleMap")
@Api(value = "用户与角色对应关系类")
public class UserRoleMapController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleMapController.class);

    @Autowired
    private UserRoleMapService userRoleMapService;

    @ApiOperation(value="更新用户角色对应关系", notes="更新用户角色对应关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "String")
    })
    @PostMapping("update")
    public Result update(@RequestBody UserRoleMapDto userRoleMapDto){
        userRoleMapDto.setCreateTime(new Date());
        userRoleMapDto.setUpdateTime(new Date());
        Result result = userRoleMapService.setUserRoleMap(userRoleMapDto);
        logger.info("结果："+result.getMsg());
        return result;
    }
}
