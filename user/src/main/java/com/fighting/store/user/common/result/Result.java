package com.fighting.store.user.common.result;

import com.fighting.store.user.common.constants.ErrorEnum;
import com.fighting.store.user.common.exception.DefinitionException;

public class Result<T> {
    public Boolean getSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    //是否成功
    private Boolean success;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    //状态码
    private String code;
    //提示信息
    private String msg;
    //数据
    private T data;
    public Result() {
    }
    //自定义返回结果的构造方法
    public Result(Boolean success,String code, String msg,T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //自定义异常返回的结果
    public static Result defineError(DefinitionException de){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(de.getErrorCode());
        result.setMsg(de.getErrorMsg());
        result.setData(null);
        return result;
    }


    //其他异常处理方法返回的结果
    public static Result otherError(ErrorEnum errorEnum){
        Result result = new Result();
        result.setMsg(errorEnum.getErrorMsg());
        result.setCode(errorEnum.getErrorCode());
        result.setSuccess(false);
        result.setData(null);
        return result;
    }
}