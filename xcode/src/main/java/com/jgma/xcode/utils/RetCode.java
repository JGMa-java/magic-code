package com.jgma.xcode.utils;

public class RetCode {
	public static final int RET_OK = 0;
	public static final int RET_ERROR = 1;
	
	public static final int RET_MISSING_SESSION = 1001;
	public static final int RET_TOKEN_NOT_EXISTS = 1002;
	public static final int RET_TOKEN_DISMATCH = 1003;
	public static final int RET_TOKEN_EXPIRED = 1004;
	public static final int RET_INVALID_TOKEN = 1005;
	public static final int RET_KICK_OFF = 1006;
	
	public static String toString(int retCode) {
		return new String("{\"retCode\":" + retCode + "}");
	}
	
}
