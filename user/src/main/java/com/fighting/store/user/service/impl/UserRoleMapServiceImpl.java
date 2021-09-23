package com.fighting.store.user.service.impl;

import com.fighting.store.user.common.constants.ErrorEnum;
import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.common.utils.BeanUtils;
import com.fighting.store.user.dao.UserRoleMapMapper;
import com.fighting.store.user.dto.UserRoleMapDto;
import com.fighting.store.user.entity.UserRoleMap;
import com.fighting.store.user.entity.UserRoleMapExample;
import com.fighting.store.user.service.UserRoleMapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserRoleMapServiceImpl implements UserRoleMapService {

    @Resource
    UserRoleMapMapper userRoleMapMapper;

    @Transactional(rollbackFor = {Exception.class})
    public Result setUserRoleMap(UserRoleMapDto userRoleMapDto){
        Result result = new Result();
        //删除用户既往角色信息
        try {
            UserRoleMapExample userRoleMapExample = new UserRoleMapExample();
            UserRoleMapExample.Criteria criteria = userRoleMapExample.createCriteria();
            criteria.andUserIdEqualTo(userRoleMapDto.getUserId());
            int deleteFlag = userRoleMapMapper.deleteByExample(userRoleMapExample);
            if(deleteFlag > 0){
                //添加用户角色信息
                UserRoleMap userRoleMap = new UserRoleMap();
                BeanUtils.copyProperties(userRoleMapDto, userRoleMap);
                int insertFlag = userRoleMapMapper.insertSelective(userRoleMap);
            }
        }catch (Exception e){
            result.setCode(ErrorEnum.UPDATE_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.UPDATE_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        return result;
    }
}
