package com.jgma.xcode.quartz.service;

import com.jgma.xcode.quartz.job.LeaveStartJob;
import com.jgma.xcode.quartz.pojo.LeaveApplication;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: admin
 */
@Service
public class LeaveApplicationService {
    @Autowired
    private Scheduler scheduler;

    // 省略其他方法与其他依赖

    /**
     * 添加job和trigger到scheduler
     */
    private void addJobAndTrigger(LeaveApplication leaveApplication) {
        Long proposerUsername = leaveApplication.getProposerUsername();
        // 创建请假开始Job
        LocalDateTime startTime = leaveApplication.getStartTime();
        JobDetail startJobDetail = JobBuilder.newJob(LeaveStartJob.class)
                // 指定任务组名和任务名
                .withIdentity(leaveApplication.getStartTime().toString(),
                        proposerUsername + "_start")
                // 添加一些参数，执行的时候用
                .usingJobData("username", proposerUsername)
                .usingJobData("time", startTime.toString())
                .build();
        // 创建请假开始任务的触发器
        // 创建cron表达式指定任务执行的时间，由于请假时间是确定的，所以年月日时分秒都是确定的，这也符合任务只执行一次的要求。
        String startCron = String.format("%d %d %d %d %d ? %d",
                startTime.getSecond(),
                startTime.getMinute(),
                startTime.getHour(),
                startTime.getDayOfMonth(),
                startTime.getMonth().getValue(),
                startTime.getYear());
        CronTrigger startCronTrigger = TriggerBuilder.newTrigger()
                // 指定触发器组名和触发器名
                .withIdentity(leaveApplication.getStartTime().toString(),
                        proposerUsername + "_start")
                .withSchedule(CronScheduleBuilder.cronSchedule(startCron))
                .build();

        // 将job和trigger添加到scheduler里
        try {
            scheduler.scheduleJob(startJobDetail, startCronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("添加请假任务失败");
        }
    }
}
