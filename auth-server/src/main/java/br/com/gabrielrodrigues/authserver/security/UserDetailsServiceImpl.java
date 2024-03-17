package br.com.gabrielrodrigues.authserver.security;

import br.com.gabrielrodrigues.authserver.repository.UserRepository;
import br.com.gabrielrodrigues.authserver.model.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("UserNotFound!"));
        final var simpleGratedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getType());
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(simpleGratedAuthority));
    }
}
