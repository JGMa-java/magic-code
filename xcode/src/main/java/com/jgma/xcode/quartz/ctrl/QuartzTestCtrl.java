package com.jgma.xcode.quartz.ctrl;

import com.jgma.xcode.quartz.job.SendPrizeJob;
import com.jgma.xcode.quartz.pojo.ScheduleJobEntity;
import com.jgma.xcode.quartz.pojo.SendPrizeJobEntity;
import com.jgma.xcode.quartz.schedule.ScheduleJobFactory;
import com.jgma.xcode.utils.BaseRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("/quartz")
public class QuartzTestCtrl {

    @Autowired
    private ScheduleJobFactory scheduleJobFactory;

    @RequestMapping("/addjob")
    public void addJob() {
        SendPrizeJobEntity j = new SendPrizeJobEntity();

        j.setConcurrent(false);
        j.setCronExpression("0/4 * * * * ?");
        j.setJobStatus("1");
        j.setParameters(null);
        j.setJobClass(SendPrizeJob.class.getName());
        j.setRequestsRecovery(true);
        j.setDruable(true);
        j.setUpdateData(true);
        j.setDescription("54645654646464.");
        try {
            scheduleJobFactory.initJob(j);
            System.out.println("添加job成功!");
        } catch (Exception e) {
            System.out.println("添加job异常!");
            e.printStackTrace();
        }
    }

    @RequestMapping("/stopjobbyname")
    public void stopJobByName() {
        SendPrizeJobEntity spj = new SendPrizeJobEntity();
        scheduleJobFactory.stopJob(spj.getJobName(), spj.getJobGroup());
        System.out.println("成功停止job");
        return;
    }

    @RequestMapping("/restartjob")
    public void restartJob() {
        SendPrizeJobEntity spj = new SendPrizeJobEntity();
        scheduleJobFactory.restartJob(spj.getJobName(), spj.getJobGroup());
        System.out.println("恢复job");
        return;
    }

    @RequestMapping("/deletejob")
    public void deleteJob() {
        SendPrizeJobEntity spj = new SendPrizeJobEntity();
        scheduleJobFactory.deleteJob(spj.getJobName(), spj.getJobGroup());
    }

    /**
     * 暂停所有job
     */
    @RequestMapping("/pauseall")
    public void stopAll() {
        scheduleJobFactory.pauseAll();
        System.out.println("暂停所有job,重启项目不启动");
        return;
    }

    @RequestMapping("/shutdownall")
    public void shutdownAll() {
        scheduleJobFactory.shutdown();
        System.out.println("已经关闭所有job,重启项目会启动");
        return;
    }

    /**
     * 恢复所有job
     */
    @RequestMapping("/stratall")
    public void restartAll() {
        scheduleJobFactory.restartAll();
        System.out.println("恢复所有job");
        return;
    }

}
