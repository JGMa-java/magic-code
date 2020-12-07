package com.jgma.xcode.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: admin
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${xcode.redis.cache.initialCacheNamesAndDuration}")
    private String[] initialCacheNames;

    private static final Pattern pattern = Pattern.compile("(?:([-+]?[0-9]+)M)|(?:([-+]?[0-9]+)H)|(?:([-+]?[0-9]+)S)", Pattern.CASE_INSENSITIVE);

    private static final String regStr = "m|M|s|S|h|H";

    /**
     * 配置redisTemplate序列化方式,使用默认的序列化manager查看会显示乱码
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 使用Jackson2JsonRedisSerialize 替换默认的jdkSerializeable序列化redis的value值
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        Map<String, RedisCacheConfiguration> initialCacheConfigurations = new LinkedHashMap();

        for (String initialCacheName : initialCacheNames) {
            String[] split = initialCacheName.split(":");
            String cacheName = split[0];
            String durationStr = split[1];

            Duration duration = null;
            try {
                duration = parseDuration(durationStr);
            } catch (Exception e) {
                log.error(e.getMessage());
                continue;
            }
            RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(duration);
            initialCacheConfigurations.put(cacheName, cacheConfiguration);
        }
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisCacheWriter).withInitialCacheConfigurations(initialCacheConfigurations).build();

        return redisCacheManager;
    }

    /**
     * 过期时间配置转Duration,仅支持时分秒格式
     *
     * @param duration 需要匹配的配置时间；例 - 2s
     * @return
     * @throws RuntimeException 不支持的时间格式
     */
    private Duration parseDuration(@Nullable String duration) throws RuntimeException {
        Matcher matcher = pattern.matcher(duration);
        if (!matcher.matches()) {
            throw new RuntimeException("[过期时间仅支持h,s,m格式]当前参数：{" + duration + "}");
        }
        if (duration.contains("m") || duration.contains("M")) {
            return Duration.ofMinutes(Long.valueOf(duration.replaceAll(regStr, "")));
        } else if (duration.contains("s") || duration.contains("S")) {
            return Duration.ofSeconds(Long.valueOf(duration.replaceAll(regStr, "")));
        } else {
            return Duration.ofHours(Long.valueOf(duration.replaceAll(regStr, "")));
        }

    }

}
