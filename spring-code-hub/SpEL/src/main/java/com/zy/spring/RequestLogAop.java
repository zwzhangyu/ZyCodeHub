package com.zy.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.expression.ExpressionParser;

import java.lang.reflect.Method;


@Aspect
@Component
public class RequestLogAop {

    static final Logger logger = LoggerFactory.getLogger(RequestLogAop.class);

    // 表达式解析模板，在 {{  }} 中的内容，会被当作 SpEL 表达式进行解析
    static final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext("{{", "}}");

    static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    private final ApplicationContext applicationContext;

    // 通过构造函数注入 ApplicationContext
    public RequestLogAop(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    // 拦截所有注解了 @RequestLog 的方法
    @Pointcut(value = "@annotation(com.zy.spring.RequestLog)")
    public void controller() {};

    // 在目标方法执行前执行
    @Before(value = "controller()")
    public void requestLog (JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法的参数
        Object[] args = joinPoint.getArgs();
        // 参数的名称
        String[] parameterNames = signature.getParameterNames();
        // 目标方法
        Method targetMethod = signature.getMethod();
        // 方法上的 @RequestLog 注解
        RequestLog log = targetMethod.getAnnotation(RequestLog.class);
        try {
            /**
             * 创建 EvaluationContext
             */
            StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
            // 设置 ApplicationContext 到 Context 中，这样的话，可以通过 @beanname 表达式来访问 IOC 中的 Bean。
            evaluationContext.setBeanResolver(new BeanFactoryResolver(this.applicationContext));
            for (int i = 0; i < args.length; i ++) {
                // 把 Controller 方法中的参数都设置到 context 中，使用参数名称作为 key。
                evaluationContext.setVariable(parameterNames[i], args[i]);
            }
            // 解析注解上定义的表达式，获取到结果
            String result = EXPRESSION_PARSER.parseExpression(log.value(), TEMPLATE_PARSER_CONTEXT).getValue(evaluationContext, String.class);
            logger.info(result);
        } catch (Exception e) {
            logger.error("操作日志SpEL表达式解析异常: {}", e.getMessage());
        }
    }
}