package com.jgma.xcode.validation.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: admin
 */
@Data
public class PersonRel {

    @NotBlank
    private String time;
}
