package com.example.es.controller;

import cn.easyes.annotation.rely.Analyzer;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.core.biz.EsPageInfo;
import cn.easyes.core.biz.SAPageInfo;
import cn.easyes.core.conditions.LambdaEsIndexWrapper;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.toolkit.EsWrappers;
import com.example.es.factory.UserInfoFactory;
import com.example.es.mapper.UserInfoMapper;
import com.example.es.model.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/userinfo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final UserInfoMapper userInfoMapper;

    @Setter
    private RestHighLevelClient client;

    @PostMapping("/insert")
    public String insert() {
        UserInfo userInfo = UserInfoFactory.generateUserInfo();
        userInfoMapper.insert(userInfo);
        log.info("添加数据：{}", userInfo);
        return "OK";
    }

    @PostMapping("/updateIndex")
    public String updateIndex() {
        LambdaEsIndexWrapper<UserInfo> wrapper = new LambdaEsIndexWrapper<>();
        // 此处简单起见 索引名称须保持和实体类名称一致,字母小写 后面章节会教大家更如何灵活配置和使用索引
        wrapper.indexName("user_info");
        // 此处将文章标题映射为keyword类型(不支持分词),文档内容映射为text类型,可缺省
        // 支持分词查询,内容分词器可指定,查询分词器也可指定,,均可缺省或只指定其中之一,不指定则为ES默认分词器(standard)
        wrapper.mapping(UserInfo::getAge, FieldType.KEYWORD);
        // 如果上述简单的mapping不能满足你业务需求,可自定义mapping
        // wrapper.mapping(Map);
        // 设置分片及副本信息,3个shards,2个replicas,可缺省
        wrapper.settings(3,2);
        // 设置别名信息,可缺省
//        String aliasName = "daily";
//        wrapper.createAlias(aliasName);
        userInfoMapper.updateIndex(wrapper);
        return "OK";
    }


    /**
     * 分词测试
     */
    @GetMapping("/match")
    public EsPageInfo<UserInfo> match(String word) {
        LambdaEsQueryWrapper<UserInfo> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(UserInfo::getContent, word);
        return EsPageInfo.of(userInfoMapper.selectList(wrapper));
    }


    /**
     * 测试插入10万条数据，每组100条数据
     */
    @PostMapping("/testBatchInsert")
    public String testBatchInsert() {
        for (int i = 0; i < 1000; i++) {
            userInfoMapper.insertBatch(UserInfoFactory.generateUserInfoList(100));
        }
        return "OK";
    }

    @PostMapping("/insertBatch")
    public Integer batchInsert() {
        return userInfoMapper.insertBatch(UserInfoFactory.generateUserInfoList());
    }

    @DeleteMapping("/delete")
    public Integer deleteById(String id) {
        return userInfoMapper.deleteById(id);
    }

    @PutMapping("/update")
    public Integer updateById(String id) {
        UserInfo userInfo = UserInfoFactory.generateUserInfo();
        userInfo.setId(id);
        log.info("修改后的数据：{}", userInfo);
        return userInfoMapper.updateById(userInfo);
    }


    @GetMapping("/search")
    public List<UserInfo> search() {
        LambdaEsQueryWrapper<UserInfo> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(UserInfo::getUserName, "程序员技术");
        return userInfoMapper.selectList(wrapper);
    }

    @GetMapping("/testPageQuery")
    public EsPageInfo<UserInfo> testPageQuery(String content, Integer page, Integer pageSize) {
        LambdaEsQueryWrapper<UserInfo> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(UserInfo::getContent, content);
        return userInfoMapper.pageQuery(wrapper, page, pageSize);
    }

    @GetMapping("/testSearchAfter")
    public SAPageInfo<UserInfo> testSearchAfter() {
        LambdaEsQueryWrapper<UserInfo> lambdaEsQueryWrapper = EsWrappers.lambdaQuery(UserInfo.class);
        lambdaEsQueryWrapper.size(10);
        // 必须指定一种排序规则,且排序字段值必须唯一 此处我选择用id进行排序 实际可根据业务场景自由指定,不推荐用创建时间,因为可能会相同
        lambdaEsQueryWrapper.orderByDesc(UserInfo::getId);
        SAPageInfo<UserInfo> saPageInfo = userInfoMapper.searchAfterPage(lambdaEsQueryWrapper, null, 10);
        // 获取下一页
        List<Object> nextSearchAfter = saPageInfo.getNextSearchAfter();
        SAPageInfo<UserInfo> next = userInfoMapper.searchAfterPage(lambdaEsQueryWrapper, nextSearchAfter, 10);
        return next;
    }

    @GetMapping("/testGroupBy")
    public Object testGroupBy() {
        LambdaEsQueryWrapper<UserInfo> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.likeRight(UserInfo::getContent, "测试");
        wrapper.groupBy(UserInfo::getUserName);
        return userInfoMapper.search(wrapper);
    }


}
