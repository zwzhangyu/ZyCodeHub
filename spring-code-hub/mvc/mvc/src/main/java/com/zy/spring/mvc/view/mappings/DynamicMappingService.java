package com.zy.spring.mvc.view.mappings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/*******************************************************
 * Created by ZhangYu on 2024/6/30
 * Description : 测试RequestMappingHandlerMapping
 * History   :
 *******************************************************/
@Service
public class DynamicMappingService {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public DynamicMappingService(RequestMappingHandlerMapping handlerMapping) {
        this.requestMappingHandlerMapping = handlerMapping;
    }

    /**
     * 注册URL映射
     */
    public void registerMapping(String urlPattern, Object handler, Method method) {
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths(urlPattern).build();
        requestMappingHandlerMapping.registerMapping(mappingInfo, handler, method);
    }

    /**
     * 获取当前应用中所有的 URL 映射信息
     */
    public void listHandlerMethods() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        // 输出每个 URL 映射的详细信息
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();
            // 输出 URL Pattern
            if (handlerMethod.getMethodAnnotation(RequestMapping.class) != null) {
                PatternsRequestCondition patternsCondition = mappingInfo.getPatternsCondition();
                if (patternsCondition != null) {
                    Set<String> patterns = patternsCondition.getPatterns();
                    System.out.println("URL Pattern: " + patterns);
                }
            }
            // 输出请求方法（GET/POST 等）
            Set<RequestMethod> methods = mappingInfo.getMethodsCondition().getMethods();
            System.out.println("Request Method: " + methods);

            // 输出处理该 URL 的 Controller 类和方法名
            System.out.println("Controller Class: " + handlerMethod.getBeanType().getName());
            System.out.println("Method Name: " + handlerMethod.getMethod().getName());

            System.out.println("---------------------------------------");
        }
    }

}
