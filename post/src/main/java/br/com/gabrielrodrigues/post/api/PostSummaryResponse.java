package br.com.gabrielrodrigues.post.api;

import br.com.gabrielrodrigues.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSummaryResponse {
    private Long id;
    private String title;

    public static PostSummaryResponse of(Post post) {
        return new PostSummaryResponse(post.getId(), post.getTitle());
    }
}
