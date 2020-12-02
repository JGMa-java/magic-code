package com.jgma.xcode.redis.producer;

import com.jgma.xcode.redis.pojo.MessageQueue;
import com.jgma.xcode.utils.JackSonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: admin
 */
public class MessageProducer extends Thread{

    public static final String MESSAGE_KEY = "message:queue";

    private RedisTemplate redisTemplate;

    public MessageProducer(){

    }

    public MessageProducer(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void putMessage(String message) {
        ListOperations listOperations = redisTemplate.opsForList();

        listOperations.leftPush(MESSAGE_KEY, message);
        redisTemplate.expire(MESSAGE_KEY,30, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String name = Thread.currentThread().getName();
            MessageQueue queue = new MessageQueue();
            queue.setTitle("redis消息队列");
            queue.setChannel("xcode:"+name);
            queue.setNumNo(i+1+"");
            putMessage(JackSonUtils.getInstance().toJSONString(queue));
        }
    }
}
