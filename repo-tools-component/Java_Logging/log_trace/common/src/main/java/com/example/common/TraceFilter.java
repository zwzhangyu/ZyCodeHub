package com.example.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebFilter("/**")
@Component
public class TraceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         log.info("init TraceFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 从请求头中获取traceId
        String traceId = request.getHeader("traceId");
        //判断traceId是否存在，不存在则创建
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put("traceId", traceId);
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("destroy TraceFilter");
    }
}