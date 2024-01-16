package com.myhd.freemarker.controller;

import com.myhd.freemarker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: HelloController
 * <br></br>
 * className: HelloController
 * <br></br>
 * packageName: com.myhd.freemarker.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 18:55
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/basic")
    public String test(Model model){
        model.addAttribute("name", "freemarker");
        Student student = new Student();
        student.setName("小米");
        student.setAge(18);
        model.addAttribute("stu", student);
        return "01-basic";
    }

}
