package msa.board.like.service.response;

import lombok.Getter;
import msa.board.like.entity.ArticleLike;

import java.time.LocalDateTime;

@Getter
public class ArticleLikeResponse {
    private Long articleLikeId;
    private Long articleId;
    private Long userId;
    private LocalDateTime localDateTime;

    public static ArticleLikeResponse from(ArticleLike articleLike) {
        ArticleLikeResponse articleLikeResponse = new ArticleLikeResponse();
        articleLikeResponse.articleLikeId = articleLike.getArticleLikeId();
        articleLikeResponse.articleId = articleLike.getArticleId();
        articleLikeResponse.userId = articleLike.getUserId();
        articleLikeResponse.localDateTime = articleLike.getCreatedAt();
        return articleLikeResponse;
    }
}
