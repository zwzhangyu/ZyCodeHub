package com.zy.spring.mvc.converts;

import com.zy.spring.mvc.pojo.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试InitBinder注解
 *
 * @author zhangyu
 * @date 2022/11/23
 **/
@RestController
@RequestMapping("/initBinder")
public class InitBinderExampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @PostMapping("/dateFormat")
    public ResponseEntity dateFormat(Date date, User user) {
        System.out.println("Date====" + date);
        System.out.println("Object Date====" + user.getBirthday());
        return ResponseEntity.ok("ok");
    }

}
