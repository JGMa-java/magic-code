package com.jgma.xcode.quartz.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * 注册无周期性的定时任务:
 * 1、学生请假，显然请假是不定时的，一次性的，而且不具有周期性。
 * 2、业务逻辑是在老师批准学生的请假申请时，向调度器添加Trigger和JobDetail。
 * @Author: admin
 */
public class LeaveStartJob extends QuartzJobBean {

    private Scheduler scheduler;

    @Autowired
    public LeaveStartJob(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        // 将添加任务的时候存进去的数据拿出来
        long username = jobDataMap.getLongValue("username");
        LocalDateTime time = LocalDateTime.parse(jobDataMap.getString("time"));

        // 编写任务的逻辑

        // 执行之后删除任务
        try {
            // 暂停触发器的计时
            scheduler.pauseTrigger(trigger.getKey());
            // 移除触发器中的任务
            scheduler.unscheduleJob(trigger.getKey());
            // 删除任务
            scheduler.deleteJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
