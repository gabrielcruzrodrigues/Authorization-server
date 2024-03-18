package br.com.gabrielrodrigues.authserver.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("aw.auth")
@Getter
@Setter
public class AuthProperties {

    @NotBlank
    private String providerUri;
}
