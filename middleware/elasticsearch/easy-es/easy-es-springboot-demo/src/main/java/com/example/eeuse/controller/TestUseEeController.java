package com.example.eeuse.controller;

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.example.eeuse.mapper.DocumentMapper;
import com.example.eeuse.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 测试使用Easy-ES
 * <p>
 * Copyright © 2021 xpc1024 All Rights Reserved
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUseEeController {

    private final DocumentMapper documentMapper;

    @GetMapping("/insert")
    public Integer insert() {
        // 初始化-> 新增数据
        Document document = new Document();
        document.setTitle("程序员技术");
        document.setContent("Easy-Es是一款简化ElasticSearch搜索引擎操作的开源框架,全自动智能索引托管.目前功能丰富度和易用度及性能已全面领先SpringData-Elasticsearch.简化CRUD及其它高阶操作,可以更好的帮助开发者减轻开发负担。底层采用Es官方提供的RestHighLevelClient,保证其原生性能及拓展性.");
        return documentMapper.insert(document);
    }

    @GetMapping("/search")
    public List<Document> search() {
        // 查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "程序员技术");
        return documentMapper.selectList(wrapper);
    }
}
