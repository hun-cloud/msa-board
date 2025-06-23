package msa.board.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.board.common.event.payload.*;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum EventType {
    ARTICLE_CREATED(ArticleCreatedEventPayload.class, Topic.ARTICLE),
    ARTICLE_UPDATED(ArticleUpdatedEventPayload.class, Topic.ARTICLE),
    ARTICLE_DELETED(ArticleDeletedEventPayload.class, Topic.ARTICLE),
    COMMENT_CREATED(CommentCreatedEventPayload.class, Topic.COMMENT),
    COMMENT_DELETED(CommentDeletedEventPayload.class, Topic.COMMENT),
    ARTICLE_LIKED(ArticleLikedEventPayload.class, Topic.LIKE),
    ARTICLE_UNLIKED(ArticleUnlikedEventPayload.class, Topic.LIKE),
    ARTICLE_VIEWED(ArticleViewEventPayload.class, Topic.VIEW),
    ;

    private final Class<? extends EventPayload> payloadClass;
    private final String topic;

    public static EventType from(String type) {
        try {
            return valueOf(type);
        } catch (Exception e) {
            log.error("[EventType.from] type={}", type, e);
            return null;
        }
    }

    public static class Topic {
        public static final String ARTICLE = "msa-board-article";
        public static final String COMMENT = "msa-board-comment";
        public static final String LIKE = "msa-board-like";
        public static final String VIEW = "mas-board-view";
    }
}
