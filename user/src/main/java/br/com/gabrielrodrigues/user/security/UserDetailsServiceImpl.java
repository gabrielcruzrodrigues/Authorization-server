package br.com.gabrielrodrigues.user.security;

import br.com.gabrielrodrigues.user.repository.UserEntityRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userRepository;

    public UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
        this.userRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));

        final var simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getType().name());

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(simpleGrantedAuthority)
        );
    }
}
