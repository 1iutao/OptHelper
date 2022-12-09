package com.common.opthelperserver.exception;


/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 自定义exception
 **/
public class ServerException extends RuntimeException {
    private final String errorCode;
    private final String errorMsg;

    public ServerException(ServerError error, String errorMsg) {
        super(error.getErrorMsg() +"," + "接口返回：["+errorMsg+"]");
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg() +"," + "接口返回：["+errorMsg+"]";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
