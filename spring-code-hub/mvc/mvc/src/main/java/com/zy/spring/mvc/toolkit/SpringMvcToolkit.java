package com.zy.spring.mvc.toolkit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/
public class SpringMvcToolkit {
    private SpringMvcToolkit() {
    }

    public static void listAllHandlerMethods() {
        RequestMappingHandlerMapping handlerMapping = SpringCtxToolkit.getBean(RequestMappingHandlerMapping.class);
        // 获取当前应用中所有的 URL 映射信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        // 输出每个 URL 映射的详细信息
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();
            // 输出 URL Pattern
            if (handlerMethod.getMethodAnnotation(RequestMapping.class) != null) {
                Set<String> patterns = mappingInfo.getPatternsCondition().getPatterns();
                System.out.println("URL Pattern: " + patterns);
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


    public static Method getUrlMethodInfo(String url) {
        RequestMappingHandlerMapping handlerMapping = SpringCtxToolkit.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methods = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methods.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            PatternsRequestCondition pattern = info.getPatternsCondition();
            if (pattern != null) {
                for (String tmpUrl : pattern.getPatterns()) {
                    if (url.equals(tmpUrl)) {
                        return entry.getValue().getMethod();
                    }
                }
            }

        }
        return null;
    }
}
