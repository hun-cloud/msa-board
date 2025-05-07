package msa.board.comment.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kuke.board.common.snowflake.Snowflake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import msa.board.comment.entity.Comment;
import msa.board.comment.repository.CommentRepository;
import msa.board.comment.service.response.CommentResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class CommentApiTest {
    RestClient restClient = RestClient.create("http://localhost:9001");

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    TransactionTemplate transactionTemplate;
    Snowflake snowflake = new Snowflake();
    CountDownLatch latch = new CountDownLatch(6000);

    static final int BULK_INSERT_SIZE = 2000;
    static final int EXECUTE_COUNT = 6000;

    @Test
    @DisplayName("create")
    void create() {
        //given
        CommentResponse response1 = createComment(new CommentCreateRequest(1L, "test", null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest(1L, "test2", response1.getCommentId(), 2L));
        CommentResponse response3 = createComment(new CommentCreateRequest(1L, "test3", response1.getCommentId(), 3L));
        //when

        //then
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    @DisplayName("read")
    void read() {
        //given
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 124332443211432L)
                .retrieve()
                .body(CommentResponse.class);
        //when

        //then
    }

    @Test
    @DisplayName("delete")
    void delete() {
        restClient.delete()
                .uri("/v1/comments/{commentId}", 124332443211432L)
                .retrieve()
                .body(Void.class);
    }

    @Test
    void initialize() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            insert();
            latch.countDown();
        }
        latch.await();
        executorService.shutdown();
    }

    private void insert() {
        transactionTemplate.executeWithoutResult(status -> {
            Comment prev = null;
            for (int i = 0; i < BULK_INSERT_SIZE; i++) {
                Comment comment = Comment.create(
                        snowflake.nextId(),
                        "content",
                        i % 2 == 0 ? null : prev.getCommentId(),
                        1L,
                        1L
                );
                prev = comment;
                entityManager.persist(comment);
            }
        });
    }


    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
