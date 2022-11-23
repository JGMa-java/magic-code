package com.jgma.xcode.recursion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jgma.xcode.recursion.entity.Department;
import com.jgma.xcode.recursion.mapper.RecursionMapper;
import org.springframework.stereotype.Service;

/**
 * @author JGMa
 */
@Service
public class RecursionServiceImpl extends ServiceImpl<RecursionMapper, Department> implements RecursionService {
}
