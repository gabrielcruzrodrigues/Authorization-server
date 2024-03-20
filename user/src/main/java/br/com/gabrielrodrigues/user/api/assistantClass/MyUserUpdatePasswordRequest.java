package br.com.gabrielrodrigues.user.api.assistantClass;

import jakarta.validation.constraints.NotBlank;

public record MyUserUpdatePasswordRequest(
        @NotBlank
        String password
) {
}
