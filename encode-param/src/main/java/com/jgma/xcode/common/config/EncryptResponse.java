package com.jgma.xcode.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgma.xcode.common.BaseRes;
import com.jgma.xcode.common.annotation.Encrypt;
import com.jgma.xcode.common.util.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyAdvice 在你使用了 @ResponseBody 注解的时候才会生效
 * 自定义的 ResponseBodyAdvice 需要用 @ControllerAdvice 注解来标记。
 * @Author: admin
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<BaseRes> {

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    EncryptProperties encryptProperties;

    /**
     * supports：这个方法用来判断什么样的接口需要加密，参数 returnType 表示返回类型，
     * 我们这里的判断逻辑就是方法是否含有 @Encrypt 注解，如果有，表示该接口需要加密处理，如果没有，表示该接口不需要加密处理。
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    /**
     * 这个方法会在数据响应之前执行，也就是我们先对响应数据进行二次处理，处理完成后，才会转成 json 返回。
     * 我们这里的处理方式很简单，RespBean 中的 status 是状态码就不用加密了，另外两个字段重新加密后重新设置值即可。
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public BaseRes beforeBodyWrite(BaseRes body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if (body.getMsg() != null) {
                body.setMsg(AESUtils.encrypt(body.getMsg().getBytes(), keyBytes));
            }
            if (body.getData() != null) {
                body.setData(AESUtils.encrypt(om.writeValueAsBytes(body.getData()), keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

}
