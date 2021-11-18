package com.jgma.xcode.common.exception;

import lombok.Data;

/**
 * 自定义异常
 * @author: jgma
 * @create: 2019-10-24 17:16
 **/
@Data
public class ApiException extends RuntimeException{

    private int errorCode;

    private String errorMsg;

    public ApiException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
