package com.ej31.jeffplayground7.post;

import com.ej31.jeffplayground7.common.dto.ApiResponse;
import com.ej31.jeffplayground7.post.dto.PostRequestDto;
import com.ej31.jeffplayground7.post.dto.PostResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 생성
     * POST /api/posts
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@Valid @RequestBody PostRequestDto requestDto) {
        PostResponseDto response = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "게시글이 성공적으로 생성되었습니다."));
    }

    /**
     * 게시글 단건 조회
     * GET /api/posts/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPost(@PathVariable Long id) {
        PostResponseDto response = postService.getPost(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 게시글 목록 조회 (페이징)
     * GET /api/posts?page=0&size=10&sort=createdAt,desc
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostResponseDto>>> getAllPosts(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> response = postService.getAllPosts(pageable);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 게시글 수정
     * PUT /api/posts/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDto requestDto) {
        PostResponseDto response = postService.updatePost(id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response, "게시글이 성공적으로 수정되었습니다."));
    }

    /**
     * 게시글 삭제
     * DELETE /api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(ApiResponse.successWithoutData("게시글이 성공적으로 삭제되었습니다."));
    }
}
