package com.common.opthelpersever;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 响应结果公共类
 **/
public class ResponseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 2853694167132064157L;

    public ResponseResult() {
        put("code", 0);
        put("msg", "success");
    }

    public static ResponseResult error() {
        return error("999", "未知异常，请联系管理员！");
    }

    public static ResponseResult error(String msg) {
        return error("999", msg);
    }
    public static ResponseResult error(String code, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("code", code);
        responseResult.put("msg", msg);
        return responseResult;
    }

    public static ResponseResult success(String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("msg", msg);
        return  responseResult;
    }

    public static ResponseResult success(Map<String, List> data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("data", data);
        return responseResult;
    }

    //返回list类型封装
    public  static ResponseResult list(List list) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("data", list);
        return responseResult;
    }

    public static ResponseResult success() {
        return new ResponseResult();
    }

    @Override
    public ResponseResult put(String k, Object v) {
        super.put(k, v);
        return this;
    }
}
