package com.ej31.jeffplayground7.comment;

import com.ej31.jeffplayground7.comment.dto.CommentRequestDto;
import com.ej31.jeffplayground7.comment.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     * POST /api/posts/{postId}/comments
     */
    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto response = commentService.createComment(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 특정 게시글의 댓글 목록 조회
     * GET /api/posts/{postId}/comments
     */
    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentResponseDto> response = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(response);
    }

    /**
     * 댓글 수정
     * PUT /api/comments/{id}
     */
    @PutMapping("/api/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto response = commentService.updateComment(id, requestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * 댓글 삭제
     * DELETE /api/comments/{id}
     */
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
