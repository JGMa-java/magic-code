package com.jgma.xcode.recursion.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JGMa
 */
@Data
public class Department {
    private Integer id;
    private Integer pid;
    private String name;
    @TableField(exist = false)
    private List<Department> childList = new ArrayList<>();
}
