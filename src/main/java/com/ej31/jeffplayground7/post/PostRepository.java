package com.ej31.jeffplayground7.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository가 기본 CRUD 메서드를 제공합니다
    // 추가적인 쿼리 메서드가 필요하면 여기에 정의할 수 있습니다
}
