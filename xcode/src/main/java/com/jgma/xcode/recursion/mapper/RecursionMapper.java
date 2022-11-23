package com.jgma.xcode.recursion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jgma.xcode.recursion.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecursionMapper extends BaseMapper<Department> {
}
