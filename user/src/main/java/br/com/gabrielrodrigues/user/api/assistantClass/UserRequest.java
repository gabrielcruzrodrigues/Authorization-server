package br.com.gabrielrodrigues.user.api.assistantClass;

import br.com.gabrielrodrigues.user.domain.Type;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Type type;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .type(this.type)
                .build();
    }
}
