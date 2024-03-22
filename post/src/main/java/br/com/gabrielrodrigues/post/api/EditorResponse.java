package br.com.gabrielrodrigues.post.api;

import br.com.gabrielrodrigues.post.client.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditorResponse {
    private Long id;
    private String name;

    public static EditorResponse of(UserResponse editor) {
        return new EditorResponse(editor.getId(),editor.getName());
    }

    public static EditorResponse anonymousEditor(Long id){
        return new EditorResponse(id,"Anônimo");
    }
}
