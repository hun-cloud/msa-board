package msa.board.view.service;

import lombok.RequiredArgsConstructor;
import msa.board.view.repository.ArticleViewCountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleViewService {
    private final ArticleViewCountRepository articleViewCountRepository;

    public Long increase(Long articleId, Long userId) {
        return articleViewCountRepository.increase(articleId);
    }

    public Long count(Long articleId) {
        return articleViewCountRepository.read(articleId);
    }
}
