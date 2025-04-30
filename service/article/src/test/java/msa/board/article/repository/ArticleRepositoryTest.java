package msa.board.article.repository;

import lombok.extern.slf4j.Slf4j;
import msa.board.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    @DisplayName("전체 조회를 테스트한다.")
    void findAllTest() {
        //given

        //when
        List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);

        //then
        log.info("article size = {}", articles.size());
        for (Article article: articles) {
            log.info("article = {}", article);
        }
    }

    @Test
    @DisplayName("카운트를 조회한다.")
    void countTest() {
        //given

        //when
        Long count = articleRepository.count(1L, 10000);
        log.info("count = {}", count);
        //then
    }
}