package br.com.gabrielrodrigues.user.api.assistantClass;

import br.com.gabrielrodrigues.user.domain.Type;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUserRegisterRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .type(Type.CLIENT)
                .build();
    }
}

