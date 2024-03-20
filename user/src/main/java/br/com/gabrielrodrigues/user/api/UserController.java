package br.com.gabrielrodrigues.user.api;

import br.com.gabrielrodrigues.user.api.assistantClass.UserRequest;
import br.com.gabrielrodrigues.user.api.assistantClass.UserResponse;
import br.com.gabrielrodrigues.user.api.assistantClass.UserUpdateRequest;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import br.com.gabrielrodrigues.user.exception.ResourceNotfoundException;
import br.com.gabrielrodrigues.user.repository.UserEntityRepository;
import br.com.gabrielrodrigues.user.security.accessInterface.CanReadUsers;
import br.com.gabrielrodrigues.user.security.accessInterface.CanWriteUsers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserEntityRepository userRepository;

    @GetMapping
    @CanReadUsers
    public Page<UserResponse> findAll(Pageable pageable) {
        final Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        final var userResponses = userEntityPage.getContent()
                .stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
        return new PageImpl<>(userResponses, pageable, userEntityPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CanWriteUsers
    public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
        return UserResponse.of(userRepository.save(userRequest.toEntity()));
    }

    @GetMapping("/{id}")
    @CanReadUsers
    public UserResponse findById(@PathVariable Long id) {
        return UserResponse.of(userRepository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Usuário não encontrado.")));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CanWriteUsers
    public UserResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Usuário não encontrado."));
        request.update(user);
        userRepository.save(user);
        return UserResponse.of(user);
    }
}
