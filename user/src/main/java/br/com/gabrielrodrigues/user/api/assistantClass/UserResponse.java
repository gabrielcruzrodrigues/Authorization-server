package br.com.gabrielrodrigues.user.api.assistantClass;

import br.com.gabrielrodrigues.user.domain.Type;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private Type type;

    public static UserResponse of(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType());
    }
}
