package com.example.eeuse.controller;

import cn.easyes.core.biz.EsPageInfo;
import cn.easyes.core.biz.SAPageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.toolkit.EsWrappers;
import com.example.eeuse.factory.UserInfoFactory;
import com.example.eeuse.mapper.UserInfoMapper;
import com.example.eeuse.model.UserInfo;
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
