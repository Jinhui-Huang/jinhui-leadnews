package com.myhd.freemarker.entity;

import lombok.Data;

import java.util.Date;

/**
 * Description: Student
 * <br></br>
 * className: Student
 * <br></br>
 * packageName: com.myhd.freemarker.entity
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 18:53
 */
@Data
public class Student {
    private String name;
    private Integer age;
    private Date birthDay;
    private Float money;
}
