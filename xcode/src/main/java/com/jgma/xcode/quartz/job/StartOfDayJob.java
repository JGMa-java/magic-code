package com.jgma.xcode.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @Author: admin
 */
@Component
public class StartOfDayJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(StartOfDayJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("执行quartz任务(StartOfDayJob)");

    }
}
