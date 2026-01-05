package com.ej31.jeffplayground7.comment;

import com.ej31.jeffplayground7.comment.dto.CommentRequestDto;
import com.ej31.jeffplayground7.comment.dto.CommentResponseDto;
import com.ej31.jeffplayground7.common.exception.ResourceNotFoundException;
import com.ej31.jeffplayground7.post.Post;
import com.ej31.jeffplayground7.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 생성
     */
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글", postId));

        Comment comment = new Comment(requestDto.getContent(), requestDto.getAuthor(), post);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    /**
     * 특정 게시글의 댓글 목록 조회
     */
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        // 게시글 존재 여부 확인
        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글", postId));

        return commentRepository.findByPostId(postId)
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("댓글", id));

        comment.update(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("댓글", id));

        commentRepository.delete(comment);
    }
}
