package com.common.opthelperserver.exception;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 枚举异常类
 **/
public enum ServerError {

    UNKNOW("000", "未知异常"),
    PARAMETER_CANNOT_BE_NULL("001", "参数不能为空"),
    USER_IS_NOT_EXIST("002", "用户不存在"),
    PASSWORD_ERROR("003", "密码错误");

    private final String errorCode;
    private final String errorMsg;

    private ServerError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ServerError getMsg(String errorCode) {
        for (ServerError value : ServerError.values()) {
            if (errorCode.equals(value.getErrorCode())) {
                return value;
            }
        }
        return UNKNOW;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
