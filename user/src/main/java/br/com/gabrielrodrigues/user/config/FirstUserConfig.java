package br.com.gabrielrodrigues.user.config;

import br.com.gabrielrodrigues.user.repository.UserEntityRepository;
import br.com.gabrielrodrigues.user.domain.Type;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstUserConfig implements ApplicationRunner {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userEntityRepository.count() != 0) {
            return;
        }

        log.info("Nenhum usuário encontrado, cadstrando usuários padrão.");

        userEntityRepository.save(
                UserEntity.builder()
                        .name("Alex Silva")
                        .email("admin@email.com")
                        .password(passwordEncoder.encode("123456"))
                        .type(Type.ADMIN)
                        .build());

        userEntityRepository.save(
                UserEntity.builder()
                        .name("João da Silva")
                        .email("joao@email.com")
                        .password(passwordEncoder.encode("123456"))
                        .type(Type.CLIENT)
                        .build());
    }
}
