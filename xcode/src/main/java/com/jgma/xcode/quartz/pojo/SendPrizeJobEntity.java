package com.jgma.xcode.quartz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: admin
 */
@Data
public class SendPrizeJobEntity extends ScheduleJobEntity implements Serializable {
    private static final long serialVersionUID = 3147907826353639204L;
    private String jobName = "SendPrizeJob";
    private String jobGroup = "SendPrizeJob_Group";
}
