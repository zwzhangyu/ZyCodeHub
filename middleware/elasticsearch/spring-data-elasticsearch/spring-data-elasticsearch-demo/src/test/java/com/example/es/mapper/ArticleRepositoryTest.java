package com.example.es.mapper;

import cn.hutool.json.JSONUtil;
import com.example.es.config.Config;
import com.example.es.model.Article;
import com.example.es.model.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void save() {
        Article article = new Article("测试标题");
        article.setAuthors(asList(new Author("张三"), new Author("李四")));
        articleRepository.save(article);
    }

    @Test
    public void test2() {
        String nameToFind = "李四";
        Page<Article> articleByAuthorName
                = articleRepository.findByAuthorsName1(nameToFind, PageRequest.of(0, 10));
        System.out.println("检索结果：" + JSONUtil.toJsonStr(articleByAuthorName));
    }

}
