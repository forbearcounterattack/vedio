package com.fighting.store.user.common.constants;

public enum ErrorEnum {
    // 数据操作错误定义
    SUCCESS("000000", "success"),
    PHONE_REGEX_FAIL("110001", "手机号格式错误"),
    PHONE_EXIST("110002", "手机号已存在"),
    EMAIL_REGEX_FAIL("110003", "邮箱格式错误"),
    EMAIL_EXIST("110004", "邮箱已存在"),
    USER_EXIST("100002", "用户已存在"),
    ADD_USER_FAIL("100003", "添加用户失败"),
    UPDATE_USER_FAIL("100004", "更新用户失败"),
    USER_NOT_EXIST("100005", "用户不存在"),
    DEL_USER_FAIL("100006", "删除用户失败"),
    NO_PERMISSION("400000","你没得权限"),
    NO_AUTH("401","你能不能先登录一下"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器跑路了"),
    ;

    /** 错误码 */
    private String errorCode;

    /** 错误信息 */
    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
