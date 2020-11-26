package com.jgma.xcode.quartz.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: admin
 */
@Data
public class LeaveApplication {
    private Integer id;
    private Long proposerUsername;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime startTime;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime endTime;
    private String reason;
    private String state;
    private String disapprovedReason;
    private Long checkerUsername;
    private LocalDateTime checkTime;
}
