package com.jgma.xcode.common.exception;


import com.jgma.xcode.common.BaseRes;
import lombok.Data;

@Data
public class BaseExceptionRes extends BaseRes {
    private String requestId;
    private String path;
}
