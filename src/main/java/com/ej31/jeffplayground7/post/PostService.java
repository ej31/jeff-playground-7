package com.ej31.jeffplayground7.post;

import com.ej31.jeffplayground7.common.exception.ResourceNotFoundException;
import com.ej31.jeffplayground7.post.dto.PostRequestDto;
import com.ej31.jeffplayground7.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = requestDto.toEntity();
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    /**
     * 게시글 단건 조회 (조회수 증가)
     */
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글", id));

        // 조회수 증가
        post.increaseViewCount();

        return new PostResponseDto(post);
    }

    /**
     * 게시글 목록 조회 (페이징)
     */
    public Page<PostResponseDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostResponseDto::new);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글", id));

        post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return new PostResponseDto(post);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글", id));

        postRepository.delete(post);
    }
}
