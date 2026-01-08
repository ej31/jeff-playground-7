package com.ej31.jeffplayground7.comment;

import com.ej31.jeffplayground7.comment.dto.CommentRequestDto;
import com.ej31.jeffplayground7.comment.dto.CommentResponseDto;
import com.ej31.jeffplayground7.common.dto.ApiResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto response = commentService.createComment(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "댓글이 성공적으로 작성되었습니다."));
    }

    /**
     * 특정 게시글의 댓글 목록 조회
     * GET /api/posts/{postId}/comments
     */
    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentResponseDto> response = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 댓글 수정
     * PUT /api/comments/{id}
     */
    @PutMapping("/api/comments/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto response = commentService.updateComment(id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response, "댓글이 성공적으로 수정되었습니다."));
    }

    /**
     * 댓글 삭제
     * DELETE /api/comments/{id}
     */
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(ApiResponse.successWithoutData("댓글이 성공적으로 삭제되었습니다."));
    }
}
