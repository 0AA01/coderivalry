package com.xzj.coderivalry.framework.starter.convention.errorcode;

/**
 * 基础错误码定义
 */
public enum BaseErrorCode implements IErrorCode {

    // ========== 一级宏观错误码 客户端错误 ==========
    CLIENT_ERROR("A000001", "用户端错误"),
    USER_NAME_VERIFY_ERROR("A000100",  "用户名校验失败"),
    USER_NAME_EXIST_ERROR("A000111", "用户名已存在"),
    PASSWORD_VERIFY_ERROR("A000120", "密码校验失败"),

    // ========== 一级宏观错误码 系统执行出错 ==========
    SERVICE_ERROR("B000001", "系统执行出错"),
    USER_NOT_EXIST("B000100", "用户不存在"),

    // ========== 一级宏观错误码 调用第三方服务出错 ==========
    REMOTE_ERROR("C000001", "调用第三方服务出错");


    private final String code;
    private final String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
