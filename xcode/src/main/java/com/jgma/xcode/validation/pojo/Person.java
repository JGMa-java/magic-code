package com.jgma.xcode.validation.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: admin
 */
@Data
public class Person {

    @NotNull
    private int age;

    @NotBlank
    private String name;

    @NotEmpty
    private List<String> address;

    @Range(max = 100,min = 10)
    private int count;

    @Email
    private String email;

    /**
     * 级联校验对象需要加
     */
    @Valid
    private PersonRel personRel;
}
