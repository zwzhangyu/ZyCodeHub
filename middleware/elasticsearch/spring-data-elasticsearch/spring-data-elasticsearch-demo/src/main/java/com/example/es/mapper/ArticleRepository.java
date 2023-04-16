package com.example.es.mapper;


import com.example.es.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {


    Page<Article> findByAuthorsName1(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
    Page<Article> findByFilteredTagQuery(String tag, Pageable pageable);

    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
    Page<Article> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
}