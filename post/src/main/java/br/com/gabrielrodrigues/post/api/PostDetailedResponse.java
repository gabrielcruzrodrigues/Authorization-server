package br.com.gabrielrodrigues.post.api;

import br.com.gabrielrodrigues.post.domain.Post;
import lombok.*;

import java.time.OffsetDateTime;

import static br.com.gabrielrodrigues.post.api.EditorResponse.anonymousEditor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailedResponse {
    private Long id;
    private OffsetDateTime createdAt;
    private String title;
    private EditorResponse editor;
    private String content;

    public PostDetailedResponse(Long id, OffsetDateTime createdAt, String title, String content, EditorResponse editor) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.editor = editor;
    }

    public static PostDetailedResponse of(Post post) {
        return PostDetailedResponse.builder()
                .id(post.getId())
                .createdAt(post.getCreatedAt())
                .title(post.getTitle())
                .content(post.getContent())
                .editor(anonymousEditor(post.getId()))
                .build();
    }

    public static PostDetailedResponse of(Post post, EditorResponse editor) {
        return new PostDetailedResponse(
                post.getId(),
                post.getCreatedAt(),
                post.getTitle(),
                post.getContent(),
                editor);
    }
}
