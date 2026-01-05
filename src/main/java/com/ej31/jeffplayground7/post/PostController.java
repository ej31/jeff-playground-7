package com.ej31.jeffplayground7.post;

import com.ej31.jeffplayground7.post.dto.PostRequestDto;
import com.ej31.jeffplayground7.post.dto.PostResponseDto;
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
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        PostResponseDto response = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 게시글 단건 조회
     * GET /api/posts/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto response = postService.getPost(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 게시글 목록 조회 (페이징)
     * GET /api/posts?page=0&size=10&sort=createdAt,desc
     */
    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> response = postService.getAllPosts(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * 게시글 수정
     * PUT /api/posts/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto requestDto) {
        PostResponseDto response = postService.updatePost(id, requestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * 게시글 삭제
     * DELETE /api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
