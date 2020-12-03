package com.jgma.xcode.redis.ctrl;

import com.jgma.xcode.redis.pojo.MessageQueue;
import com.jgma.xcode.redis.producer.MessageProducer;
import com.jgma.xcode.redis.service.RedisService;
import com.jgma.xcode.utils.JackSonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created By majg on 2020-08-19
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisCtrl {

    public final String MESSAGE_KEY = "message:queue";

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send")
    public Object produce() {
        MessageProducer messageProducer = new MessageProducer(redisTemplate);
        Thread t1 = new Thread(messageProducer, "thread1");
        Thread t2 = new Thread(messageProducer, "thread2");
        Thread t3 = new Thread(messageProducer, "thread3");
        Thread t4 = new Thread(messageProducer, "thread4");
        Thread t5 = new Thread(messageProducer, "thread5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        return "ok";
    }

    /**
     * 非阻塞消费，有如下问题:
     *  1）、如果生产者速度大于消费者消费速度，消息队列长度会一直增大，时间久了会占用大量内存空间。
     *  2）、如果睡眠时间过长，这样不能处理一些时效性的消息，睡眠时间过短，也会在连接上造成比较大的开销。
     * @return
     */
    @GetMapping("/consume")
    public Object consume(){
        boolean loop = true;
        ListOperations listOperations = redisTemplate.opsForList();
        int total = 0;
        while (true){

            Object o = listOperations.rightPop(MESSAGE_KEY);
//            if (StringUtils.isEmpty(o)){
//                loop = false;
//                break;
//            }
            total ++;
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            log.info(o+threadName);
//            MessageQueue queue = JackSonUtils.getInstance().toObject(o.toString(), MessageQueue.class);
//            log.info("redis队列消费:{}",queue);
        }

//        return "共消费到"+total+"条";
    }

    /**
     * 阻塞消费，todo 有bug,超过一段时间会报链接超时redis
     * @return
     */
    @GetMapping("/bConsume")
    public Object bConsume(){
        boolean loop = true;
        int total = 0;
        while (true){
            ListOperations listOperations = redisTemplate.opsForList();
            Object o = listOperations.rightPop(MESSAGE_KEY,0, TimeUnit.SECONDS);
//            if (StringUtils.isEmpty(o)){
//                loop = false;
//                break;
//            }
            total ++;
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            log.info(o+threadName);
//            MessageQueue queue = JackSonUtils.getInstance().toObject(o.toString(), MessageQueue.class);
//            log.info("redis队列消费:{}",queue);
        }

//        return "共消费到"+total+"条";
    }

    @GetMapping("/Rewrite/{value}")
    public Object Rewrite(@PathVariable(name = "value") String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("testRewrite", value);
        return "ok";
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable(name = "key") String key) {

        return redisService.get(key);
    }

}
