package com.common.opthelpersever.config;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 异常类
 **/
public enum SeverError {

    UNKNOW("000", "未知异常"),
    PARAMETER_CANNOT_BE_NULL("001", "参数不能为空");

    private final String errorCode;
    private final String errorMsg;

    private SeverError(String errorCode, String errorMsg) {
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
