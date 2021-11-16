package com.jgma.xcode.execption;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private  Logger  logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public Response allExceptionHandler(HttpServletRequest request,
                                        Exception exception) throws Exception
    {
        logger.error("拦截到异常：", exception);
        Response response = new Response();
//        response.setData(null);
//        response.getResult().setResultCode(9999);
//        response.getResult().setResultMsg("系统繁忙");
        return response;
    }

}