package msa.board.like.controller;

import lombok.RequiredArgsConstructor;
import msa.board.like.service.ArticleLikeService;
import msa.board.like.service.response.ArticleLikeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/article/s-likes/articles")
@RequiredArgsConstructor
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    @GetMapping("/{articleId}/users/{userId}")
    public ArticleLikeResponse read(@PathVariable("articleId") Long articleId,
                                    @PathVariable("userId") Long userId) {
        return articleLikeService.read(articleId, userId);
    }

    @PostMapping("/{articleId}/users/{userId}")
    public void like(@PathVariable("articleId") Long articleId,
                     @PathVariable("userId") Long userId) {
        articleLikeService.like(articleId, userId);
    }

    @DeleteMapping("/{articleId}/users/{userId}")
    public void unlike(@PathVariable("articleId") Long articleId,
                       @PathVariable("userId") Long userId) {
        articleLikeService.unlike(articleId, userId);
    }
}
