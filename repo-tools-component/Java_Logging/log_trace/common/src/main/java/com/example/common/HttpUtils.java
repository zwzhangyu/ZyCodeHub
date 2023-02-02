package com.example.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class HttpUtils {

    public static String get(String url) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        //将traceId放在请求头中，保证服务间调用能够获取记录
        headers.add("traceId", MDC.get("traceId"));
        URI uri = new URI(url);
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        if (exchange.getStatusCode().equals(HttpStatus.OK)) {
            log.info("send http request success");
        }
        return exchange.getBody();
    }

}