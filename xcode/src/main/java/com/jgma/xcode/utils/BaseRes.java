package com.jgma.xcode.utils;

import java.io.Serializable;

public class BaseRes implements Serializable {

	private static final long serialVersionUID = 5023007363066294795L;

	public Integer status;
	public String msg;
	public Object data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static BaseRes ok() {
		return new BaseRes(RetCode.RET_OK, "success", null);
	}

	public static BaseRes ok(String msg) {
		return new BaseRes(RetCode.RET_OK, msg, null);
	}

	public static BaseRes ok(String msg, Object obj) {
		return new BaseRes(RetCode.RET_OK, msg, obj);
	}

	public static BaseRes error() {
		return new BaseRes(RetCode.RET_ERROR, "error", null);
	}

	public static BaseRes error(String msg) {
		return new BaseRes(RetCode.RET_ERROR, msg, null);
	}

	public static BaseRes error(String msg, Object obj) {
		return new BaseRes(RetCode.RET_ERROR, msg, obj);
	}

	public BaseRes put(Object obj){
		this.data = obj;
		return this;
	}

	private BaseRes() {
	}

	private BaseRes(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
}