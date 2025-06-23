package msa.board.hotarticle.service.eventhandler;

import lombok.RequiredArgsConstructor;
import msa.board.common.event.Event;
import msa.board.common.event.EventType;
import msa.board.common.event.payload.ArticleViewEventPayload;
import msa.board.hotarticle.repository.ArticleViewCountRepository;
import msa.board.hotarticle.utils.TimeCalculatorUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewedEventHandler implements EventHandler<ArticleViewEventPayload> {

    private final ArticleViewCountRepository articleViewCountRepository;

    @Override
    public void handle(Event<ArticleViewEventPayload> event) {
        ArticleViewEventPayload payload = event.getPayload();
        articleViewCountRepository.createOrUpdate(
                payload.getArticleId(),
                payload.getArticleViewCount(),
                TimeCalculatorUtils.calculateDurationToMidnight()
        );
    }

    @Override
    public boolean supports(Event<ArticleViewEventPayload> event) {
        return EventType.ARTICLE_VIEWED == event.getType();
    }

    @Override
    public Long findArticleId(Event<ArticleViewEventPayload> event) {
        return event.getPayload().getArticleId();
    }
}
