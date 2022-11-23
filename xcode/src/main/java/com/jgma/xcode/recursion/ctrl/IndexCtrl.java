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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * list递归返回树
     * @param root 根节点特征的数据
     * @param all 原始数据list
     * @return
     */
    public List<Map<String, Object>> getChildrens(Map root, List<Map<String, Object>> all) {
        List<Map<String, Object>> children = all.stream().filter(m -> {
            return Objects.equals(m.get("pid"), root.get("id"));
        }).map(
                (m) -> {
                    m.put("children", getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }

}
