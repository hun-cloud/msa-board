package msa.board.comment.controller;

import lombok.RequiredArgsConstructor;
import msa.board.comment.service.CommentService;
import msa.board.comment.service.CommentServiceV2;
import msa.board.comment.service.request.CommentCreateRequest;
import msa.board.comment.service.response.CommentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/comments")
@RequiredArgsConstructor
public class CommentControllerV2 {

    private final CommentServiceV2 commentService;

    @GetMapping("/{commentId}")
    public CommentResponse read(@PathVariable("commentId") Long commentId) {
        return commentService.read(commentId);
    }

    @PostMapping
    public CommentResponse create(@RequestBody CommentCreateRequest request) {
        return commentService.create(request);
    }

    @DeleteMapping
    public void delete(@RequestParam("commentId") Long commentId) {
        commentService.delete(commentId);
    }

    @GetMapping("/v2/comments/{commentId}/count")
    public Long count(@PathVariable("commentId") Long commentId) {
        return commentService.count(commentId);
    }
}
