package com.jgma.xcode.common.exception;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandlerDemo {

    private  Logger  logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public ApiException allExceptionHandler(HttpServletRequest request,
                                        Exception exception) throws Exception
    {
        logger.error("拦截到异常：", exception);
        ApiException apiException = new ApiException(1,"访问失败！");
        return apiException;
    }

}