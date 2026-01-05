package com.ej31.jeffplayground7.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;
    private String author;

    public CommentRequestDto(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
