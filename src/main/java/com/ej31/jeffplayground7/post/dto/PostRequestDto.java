package com.ej31.jeffplayground7.post.dto;

import com.ej31.jeffplayground7.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 200, message = "제목은 200자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotBlank(message = "작성자는 필수입니다.")
    @Size(max = 50, message = "작성자는 50자 이하여야 합니다.")
    private String author;

    @Size(max = 50, message = "카테고리는 50자 이하여야 합니다.")
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
