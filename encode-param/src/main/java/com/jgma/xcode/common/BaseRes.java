package com.jgma.xcode.common;

/**
 * 通用报文
 *
 * @Author: admin
 */
public class BaseRes {
    private Integer status;
    private String msg;
    private Object data;

    public BaseRes() {
    }

    public BaseRes(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static BaseRes ok() {
        return new BaseRes(ResConstant.RET_OK, "success", null);
    }

    public static BaseRes ok(String msg) {
        return new BaseRes(ResConstant.RET_OK, msg, null);
    }

    public static BaseRes ok(String msg, Object obj) {
        return new BaseRes(ResConstant.RET_OK, msg, obj);
    }

    public static BaseRes error(String msg) {
        return new BaseRes(ResConstant.RET_ERROR, msg, null);
    }

    public static BaseRes error(Integer code, String msg) {
        return new BaseRes(code, msg, null);
    }

    public static BaseRes error(Integer code, String msg, Object obj) {
        return new BaseRes(code, msg, obj);
    }

    public Integer getStatus() {
        return status;
    }

    public BaseRes setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseRes setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseRes setData(Object data) {
        this.data = data;
        return this;
    }
}
