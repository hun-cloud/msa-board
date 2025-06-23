package msa.board.like.service;

import msa.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import msa.board.like.entity.ArticleLike;
import msa.board.like.repository.ArticleLikeRepository;
import msa.board.like.service.response.ArticleLikeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    private final Snowflake snowflake = new Snowflake();
    private final ArticleLikeRepository articleLikeRepository;

    public ArticleLikeResponse read(Long articleId, Long userId) {
        return articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .map(ArticleLikeResponse::from)
                .orElseThrow();
    }

    @Transactional
    public void like(Long articleId, Long userId) {
        if (articleLikeRepository.findByArticleIdAndUserId(articleId, userId).isPresent()) {
            throw new IllegalArgumentException("Already liked");
        }
        articleLikeRepository.save(ArticleLike.create(snowflake.nextId(), articleId, userId));
    }

    @Transactional
    public void unlike(Long articleId, Long userId) {
        ArticleLike articleLike = articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Not liked yet"));
        articleLikeRepository.delete(articleLike);
    }

}
