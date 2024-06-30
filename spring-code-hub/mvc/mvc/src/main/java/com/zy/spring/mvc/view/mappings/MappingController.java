package com.zy.spring.mvc.view.mappings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;
import java.util.Map;

/*******************************************************
 * Created by ZhangYu on 2024/6/30
 * Description : 测试RequestMappingHandlerMapping
 * History   :
 *******************************************************/
@RestController
@RequestMapping("/mappings")
public class MappingController {

    private final DynamicMappingService dynamicMappingService;
    private final MyController myController;

    @Autowired
    public MappingController(DynamicMappingService dynamicMappingService, MyController myController) {
        this.dynamicMappingService = dynamicMappingService;
        this.myController = myController;
    }

    @GetMapping
    public String getAllMappings() {
        dynamicMappingService.listHandlerMethods();
        return "ok";
    }

    @GetMapping("/register")
    public String registerNewMapping() throws NoSuchMethodException {
        Method method = MyController.class.getMethod("hello");
        dynamicMappingService.registerMapping("/api/new-hello", myController, method);
        return "注册新的映射";
    }
}
