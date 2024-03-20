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
public class UserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private Type type;

    public void update(UserEntity currentUser) {
        currentUser.setEmail(this.email);
        currentUser.setName(this.name);
        currentUser.setType(this.type);
    }
}
