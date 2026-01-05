package com.ej31.jeffplayground7.post.dto;

import com.ej31.jeffplayground7.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String author;
    private String category;

    public PostRequestDto(String title, String content, String author, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    // DTO -> Entity 변환
    public Post toEntity() {
        return new Post(title, content, author, category);
    }
}
