package com.fighting.store.user.service.impl;

import com.fighting.store.user.common.constants.ErrorEnum;
import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.common.utils.BeanUtils;
import com.fighting.store.user.dao.UserMapper;
import com.fighting.store.user.dto.UserDto;
import com.fighting.store.user.entity.User;
import com.fighting.store.user.entity.UserExample;
import com.fighting.store.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Value("${regex.phone}")
    private String phoneRegex;

    @Value("${regex.email}")
    private String emailRegex;

    @Resource
    UserMapper userMapper;
    public Integer getAllUserCount() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        List<User> userList= userMapper.selectByExample(userExample);
        return userList.size();
    }

    public Integer addUser(User user){
        return userMapper.insertSelective(user);
    }

    public Result addUser(UserDto userDto){
        Result result = new Result();
        User user = new User();
        //验证手机号格式
        boolean isPhone = Pattern.compile(StringEscapeUtils.unescapeJava(phoneRegex)).matcher(userDto.getPhone()).matches();
        if(!isPhone){
            System.out.println(userDto.getPhone());
            result.setCode(ErrorEnum.PHONE_REGEX_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.PHONE_REGEX_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        //验证邮箱格式
        boolean isMail = Pattern.compile(StringEscapeUtils.unescapeJava(emailRegex)).matcher(userDto.getEmail()).matches();
        if(!isMail){
            System.out.println(userDto.getEmail());
            result.setCode(ErrorEnum.EMAIL_REGEX_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.EMAIL_EXIST.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        //查重-根据手机号和邮箱
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria1 = userExample.createCriteria();
        criteria1.andPhoneEqualTo(userDto.getPhone());
        UserExample.Criteria criteria2 = userExample.createCriteria();
        criteria2.andEmailEqualTo(userDto.getEmail());
        userExample.or(criteria2);
        List<User> userList= userMapper.selectByExample(userExample);
        if(userList.size() > 0){
            result.setCode(ErrorEnum.ADD_USER_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.ADD_USER_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        BeanUtils.copyProperties(userDto,user);
        Integer userId = userMapper.insertSelective(user);
        if(userId <= 0){
            result.setCode(ErrorEnum.ADD_USER_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.ADD_USER_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        result.setCode(ErrorEnum.SUCCESS.getErrorCode());
        result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
        result.setSuccess(true);
        result.setData(user);
        return result;
    }

    public Result updateUser(UserDto userDto){
        Result result = new Result();
        User user = new User();
        boolean regex = true;
        if(!"".equals(userDto.getPhone())){
            //验证手机号格式
            boolean isPhone = Pattern.compile(StringEscapeUtils.unescapeJava(phoneRegex)).matcher(userDto.getPhone()).matches();
            if(!isPhone){
                System.out.println(userDto.getPhone());
                result.setCode(ErrorEnum.PHONE_REGEX_FAIL.getErrorCode());
                result.setMsg(ErrorEnum.PHONE_REGEX_FAIL.getErrorMsg());
                result.setSuccess(false);
                return result;
            }
        }
        if(!"".equals(userDto.getEmail())){
            //验证邮箱格式
            boolean isMail = Pattern.compile(StringEscapeUtils.unescapeJava(emailRegex)).matcher(userDto.getEmail()).matches();
            if(!isMail){
                System.out.println(userDto.getEmail());
                result.setCode(ErrorEnum.EMAIL_REGEX_FAIL.getErrorCode());
                result.setMsg(ErrorEnum.EMAIL_EXIST.getErrorMsg());
                result.setSuccess(false);
                return result;
            }
        }

        //查重-根据手机号
        UserExample userExample1 = new UserExample();
        UserExample.Criteria criteria1 = userExample1.createCriteria();
        criteria1.andPhoneEqualTo(userDto.getPhone());
        List<User> userList1 = userMapper.selectByExample(userExample1);
        if(userList1.size() > 0){
            User user1 = userList1.get(0);
            if(user1.getId()!=userDto.getId()){
                result.setCode(ErrorEnum.NO_PERMISSION.getErrorCode());
                result.setMsg(ErrorEnum.NO_PERMISSION.getErrorMsg());
                result.setSuccess(false);
                return result;
            }
        }
        //查重-根据邮箱
        UserExample userExample2 = new UserExample();
        UserExample.Criteria criteria2 = userExample2.createCriteria();
        criteria2.andEmailEqualTo(userDto.getEmail());
        List<User> userList2= userMapper.selectByExample(userExample2);
        if(userList2.size() > 0){
            User user2 = userList1.get(0);
            if(user2.getId()!=userDto.getId()){
                result.setCode(ErrorEnum.NO_PERMISSION.getErrorCode());
                result.setMsg(ErrorEnum.NO_PERMISSION.getErrorMsg());
                result.setSuccess(false);
                return result;
            }
        }

        BeanUtils.copyProperties(userDto,user);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(userDto.getId());
        Integer flag = userMapper.updateByExampleSelective(user,userExample);
        if(flag <= 0){
            result.setCode(ErrorEnum.UPDATE_USER_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.UPDATE_USER_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        result.setCode(ErrorEnum.SUCCESS.getErrorCode());
        result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
        result.setSuccess(true);
        result.setData(user);
        return result;
    }

    public Result deleteUser(UserDto userDto){
        Result result = new Result();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(userDto.getId());
        Integer flag = userMapper.deleteByExample(userExample);
        if(flag <= 0){
            result.setCode(ErrorEnum.DEL_USER_FAIL.getErrorCode());
            result.setMsg(ErrorEnum.DEL_USER_FAIL.getErrorMsg());
            result.setSuccess(false);
            return result;
        }
        result.setCode(ErrorEnum.SUCCESS.getErrorCode());
        result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
        result.setSuccess(true);
        result.setData(null);
        return result;
    }

    public Result getUser(Integer userId){
        Result result = new Result();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(userId);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() > 0){
            result.setCode(ErrorEnum.SUCCESS.getErrorCode());
            result.setMsg(ErrorEnum.SUCCESS.getErrorMsg());
            result.setSuccess(true);
            result.setData(userList.get(0));
        }else{
            result.setCode(ErrorEnum.USER_NOT_EXIST.getErrorCode());
            result.setMsg(ErrorEnum.USER_NOT_EXIST.getErrorMsg());
            result.setSuccess(false);
        }
        return result;
    }

    public List<User> getUserList(Integer pageNum,Integer pageSize,String searchContent){
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.selectUserByPage(searchContent);
        return userList;
    }
}
