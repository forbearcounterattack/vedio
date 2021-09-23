package com.fighting.store.user.controller;

import com.alibaba.fastjson.JSON;
import com.fighting.store.user.common.constants.ErrorEnum;
import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.entity.Role;
import com.fighting.store.user.entity.User;
import com.fighting.store.user.service.RoleService;
import com.fighting.store.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "角色管理类")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @ApiOperation(value="查询角色列表", notes="查询角色列表")
    @ApiImplicitParams({
    })
    @GetMapping("getList")
    public Result getList(){
        List<Role> roleList= roleService.getRoleList();
        Result result = new Result();
        result.setCode(ErrorEnum.SUCCESS.getErrorCode());
        result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
        result.setSuccess(true);
        result.setData(roleList);
        logger.info("结果："+ JSON.toJSONString(roleList));
        return result;
    }
}
