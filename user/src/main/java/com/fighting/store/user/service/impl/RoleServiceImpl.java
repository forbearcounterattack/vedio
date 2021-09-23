package com.fighting.store.user.service.impl;

import com.fighting.store.user.dao.RoleMapper;
import com.fighting.store.user.entity.Role;
import com.fighting.store.user.entity.RoleExample;
import com.fighting.store.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    public List<Role> getRoleList(){
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        return roleList;
    }
}
