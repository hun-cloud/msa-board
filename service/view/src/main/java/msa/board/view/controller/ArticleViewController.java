package msa.board.view.controller;

import lombok.RequiredArgsConstructor;
import msa.board.view.service.ArticleViewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/article-views/articles")
@RequiredArgsConstructor
public class ArticleViewController {

    private final ArticleViewService articleViewService;

    @PostMapping("/{articleId}/users/{userId}")
    public Long increaseViewCount(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId) {
        return articleViewService.increase(articleId, userId);
    }

    @GetMapping("/{articleId}/count")
    public Long count(@PathVariable("articleId") Long articleId) {
        return articleViewService.count(articleId);
    }
}
