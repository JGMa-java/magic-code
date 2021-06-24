package com.jgma.xcode.quartz.schedule;

import com.alibaba.fastjson.JSONObject;
import com.jgma.xcode.quartz.pojo.ScheduleJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 编写quartz添加job调度类
 * @Author: admin
 */

@Component
@Slf4j
public class ScheduleJobFactory {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 初始化任务
     *
     * @param job
     */
    public void initJob(ScheduleJobEntity job) throws Exception {
        if (job == null) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        if (!ScheduleJobEntity.STATUS_RUNNING.equals(job.getJobStatus())) {
            this.deleteJob(scheduler, triggerKey);
            log.info("删除任务{}", job.getJobName());
        } else {
            addJob(scheduler, triggerKey, job);
            log.info(scheduler + "添加任务:{}", JSONObject.toJSONString(job));
        }

    }

    /**
     * 增加定时任务job
     *
     * @param scheduler  调度器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void addJob(Scheduler scheduler, TriggerKey triggerKey, ScheduleJobEntity job) throws Exception {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            /*不存在,创建一个*/
            setJobExistTrigger(scheduler, trigger, triggerKey, job);
        } else {
            /*存在,直接用*/
            setJobNoExistTrigger(scheduler, trigger, triggerKey, job);
        }
    }

    /**
     * 如果触发器key不存在,设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void setJobNoExistTrigger(Scheduler scheduler, CronTrigger trigger, TriggerKey triggerKey, ScheduleJobEntity job)
            throws Exception {
        /*Trigger已存在，那么更新相应的定时设置*/
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        /* 按新的cronExpression表达式重新构建trigger*/
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        /*按新的trigger重新设置job执行*/
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 如果触发器key存在,设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    public void setJobExistTrigger(Scheduler scheduler, CronTrigger trigger,
                                   TriggerKey triggerKey, ScheduleJobEntity job) throws Exception {
        Class clazz = Class.forName(job.getJobClass());
        /*设定job详情和属性*/
        JobDetailImpl tail = new JobDetailImpl();
        tail.setDescription(job.getDescription());
        tail.setGroup(job.getJobGroup());
        tail.setJobClass(clazz);
        tail.setJobDataMap(new JobDataMap());
        tail.setKey(JobKey.jobKey(job.getJobName(),job.getJobGroup()));
        tail.setRequestsRecovery(job.isRequestsRecovery());
        /*设置持久*/
        tail.setDurability(job.isDruable());
        JobDetail jobDetail = tail;
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule(job.getCronExpression());

        trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName(), job.getJobGroup())
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 删除job
     *
     * @param scheduler
     * @param triggerKey
     * @throws Exception
     */
    private void deleteJob(Scheduler scheduler, TriggerKey triggerKey) {
        if (null != triggerKey) {
            try {
                scheduler.unscheduleJob(triggerKey);
            } catch (SchedulerException e) {
                log.error("删除job异常");
                e.printStackTrace();
            }
        } else {
            log.warn("删除job无效,没有指定对应的Trigger");
        }
    }

    /**
     * 停止job
     *
     * @param name  名称
     * @param group 分组
     */
    public void stopJob(String name, String group) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("停止job异常");
            e.printStackTrace();
        }
    }

    /**
     * 添加job
     *
     * @param job
     */
    public void addJob(ScheduleJobEntity job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Class clazz = null;
        try {
            clazz = Class.forName(job.getJobClass());
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            try {
                scheduler.addJob(jobDetail, true);
            } catch (SchedulerException e) {
                log.error("添加job出现异常");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止所有的job
     */
    public void pauseAll() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("暂停所有job出现异常");
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有job
     */
    public void shutdown(){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("关闭所有Job出现异常!");
            e.printStackTrace();
        }
    }

    /**
     * 恢复指定任务
     *
     * @param name
     * @param group
     */
    public void restartJob(String name, String group) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("恢复任务异常");
            e.printStackTrace();
        }
    }

    /**
     * 恢复所有job
     */
    public void restartAll() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            log.error("恢复所有job异常");
            e.printStackTrace();
        }
    }

    /**
     * 删除job
     * @param jobName job名称
     * @param jobGroup job 分组
     */
    public void deleteJob(String jobName, String jobGroup) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            /*scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));*/
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            log.error("删除job"+jobName+"."+jobGroup+"出现异常");
            e.printStackTrace();
        }
    }
}
