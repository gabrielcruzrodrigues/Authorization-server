package br.com.gabrielrodrigues.user.api.assistantClass;

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
public class MyUserUpdateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    public void update(UserEntity currentUser) {
        currentUser.setName(this.name);
        currentUser.setEmail(this.email);
    }
}
