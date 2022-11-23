package com.jgma.xcode.recursion.ctrl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jgma.xcode.recursion.entity.Department;
import com.jgma.xcode.recursion.service.RecursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JGMa
 */
@RestController
@RequestMapping("/recursion")
public class IndexCtrl {

    @Autowired
    private RecursionService recursionService;

    @GetMapping("/selectList/{pid}")
    public Object selectList(@PathVariable(value = "pid") Integer pid){
        return getDepartmentList(pid);
    }


    /**
     * 递归查询部门
     *
     * @param pid
     * @return
     */
    private List<Department> getDepartmentList(Integer pid) {

        List<Department> departments = recursionService.lambdaQuery().eq(Department::getPid, pid).list();
        if (departments.size() > 0) {
            for (int i = 0; i < departments.size(); i++) {
                List<Department> dtos = getDepartmentList(departments.get(i).getId());
                departments.get(i).setChildList(dtos);
            }
        }
        return departments;
    }

}
