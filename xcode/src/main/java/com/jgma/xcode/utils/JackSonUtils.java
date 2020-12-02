package com.jgma.xcode.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * jackson序列化工具类<br>
 * Created By majg on 2020-07-23
 */
public class JackSonUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ObjectMapper mapper;
    private volatile static JackSonUtils jackSonUtils;

    JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        // 允许对象忽略json中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JackSonUtils() {
    }

    public static JackSonUtils getInstance() {
        if (jackSonUtils == null) {
            synchronized (JackSonUtils.class) {
                if (jackSonUtils == null) {
                    jackSonUtils = new JackSonUtils();
                }
            }
        }
        return jackSonUtils;
    }

    /**
     * 指定字段序列化
     *
     * @param clazz   指定类class
     * @param include "file1,file2,file3..."
     */
    public void addIncludeFiles(Class<?> clazz, String include) {
        if (clazz == null) {
            return;
        }
        if (StringUtils.isEmpty(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJSONString(Object obj) {
        try {
            mapper.setFilterProvider(jacksonFilter);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败!");
        }
    }

    /**
     *
     * @param obj
     * @param mapNullValue false:忽视为空的属性(不序列化)
     * @return
     */
    public String toJSONString(Object obj, boolean mapNullValue) {
        try {
            mapper.setFilterProvider(jacksonFilter);
            if (!mapNullValue){
                // 忽视为空的属性
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            }
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败!");
        }
    }

    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("将json字符转换为对象时失败!");
        }
    }

    public <T> T toObject(String json, TypeReference<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("将json字符转换为对象时失败!");

        }
    }

}
