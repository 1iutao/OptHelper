package com.common.opthelpersever.exception;


/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 自定义exception
 **/
public class ServerException extends RuntimeException {
    private final String errorCode;
    private final String errorMsg;

    public ServerException(ServerError error, String name) {
        super((error.getErrorMsg()));
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
