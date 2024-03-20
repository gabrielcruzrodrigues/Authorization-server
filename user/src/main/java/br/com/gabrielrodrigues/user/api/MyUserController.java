package br.com.gabrielrodrigues.user.api;

import br.com.gabrielrodrigues.user.api.assistantClass.MyUserRegisterRequest;
import br.com.gabrielrodrigues.user.api.assistantClass.MyUserUpdatePasswordRequest;
import br.com.gabrielrodrigues.user.api.assistantClass.MyUserUpdateRequest;
import br.com.gabrielrodrigues.user.api.assistantClass.UserResponse;
import br.com.gabrielrodrigues.user.domain.UserEntity;
import br.com.gabrielrodrigues.user.exception.ResourceNotfoundException;
import br.com.gabrielrodrigues.user.repository.UserEntityRepository;
import br.com.gabrielrodrigues.user.security.accessInterface.CanEditMyUser;
import br.com.gabrielrodrigues.user.security.accessInterface.CanReadMyUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MyUserController {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @CanReadMyUser
    @GetMapping
    public UserResponse me(@AuthenticationPrincipal UserDetails userDetails) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userEntityRepository.findByEmail(userDetails.getUsername())
                .map(UserResponse::of)
                .orElseThrow(() -> new ResourceClosedException("Usuário não encontrado!"));
    }

    @CanEditMyUser
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MyUserUpdateRequest request) {
        UserEntity user = userEntityRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotfoundException("Usuário não encontrado."));
        request.update(user);
        userEntityRepository.save(user);
    }

    @CanEditMyUser
    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MyUserUpdatePasswordRequest request) {
        UserEntity user = userEntityRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotfoundException("Usuário não encontrado."));
        user.setPassword(passwordEncoder.encode(request.password()));
        userEntityRepository.save(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid MyUserRegisterRequest request) {
        UserEntity user = request.toEntity();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = this.userEntityRepository.save(user);
        return UserResponse.of(user);
    }
}
