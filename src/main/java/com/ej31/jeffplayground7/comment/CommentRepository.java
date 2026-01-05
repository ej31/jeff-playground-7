package com.ej31.jeffplayground7.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 게시글의 댓글 목록 조회 (Post를 fetch join하여 N+1 방지)
     */
    @Query("SELECT c FROM Comment c JOIN FETCH c.post WHERE c.post.id = :postId")
    List<Comment> findByPostIdWithPost(@Param("postId") Long postId);

    /**
     * 댓글 단건 조회 (Post를 fetch join하여 N+1 방지)
     */
    @Query("SELECT c FROM Comment c JOIN FETCH c.post WHERE c.id = :id")
    Optional<Comment> findByIdWithPost(@Param("id") Long id);
}
