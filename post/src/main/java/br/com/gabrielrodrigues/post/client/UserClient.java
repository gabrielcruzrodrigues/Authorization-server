package br.com.gabrielrodrigues.post.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserClient {
    private final RestTemplate restTemplate;
    private final UserClientProperties properties;

    public Optional<UserResponse> findById(Long id) {
        try {
            final String url = properties.getUrl() + "/users/{id}";
            final UserResponse response = restTemplate.getForObject(url, UserResponse.class, id);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error(String.format("Erro ao buscar usuário de id %s", id), e);
            return Optional.empty();
        }
    }
}
