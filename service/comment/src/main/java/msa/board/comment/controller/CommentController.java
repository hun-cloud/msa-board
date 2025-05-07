package msa.board.comment.controller;

import lombok.RequiredArgsConstructor;
import msa.board.comment.service.CommentService;
import msa.board.comment.service.request.CommentCreateRequest;
import msa.board.comment.service.response.CommentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

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
}
