package com.jgma.xcode.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: admin
 */
@Slf4j
public class SendPrizeJob extends QuartzJobBean {

    private Scheduler scheduler;

    @Autowired
    public SendPrizeJob(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        // 将添加任务的时候存进去的数据拿出来
//        long username = jobDataMap.getLongValue("username");
//        LocalDateTime time = LocalDateTime.parse(jobDataMap.getString("time"));

        log.info("执行sendPrize；用户名:{sendPrize},时间:{}",new Date());

        // 执行之后删除任务
//        try {
//            // 暂停触发器的计时
//            scheduler.pauseTrigger(trigger.getKey());
//            // 移除触发器中的任务
//            scheduler.unscheduleJob(trigger.getKey());
//            // 删除任务
//            scheduler.deleteJob(jobDetail.getKey());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}
