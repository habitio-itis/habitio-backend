package ru.itis.habitio.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.habitio.web.dto.response.UserResponse;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JwtAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        UserResponse userResponse = (UserResponse) token.getPrincipal();

        return Optional.ofNullable(userResponse)
                .map(user -> HabitioUserDetails.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(userResponse.getEmail())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
    }
}
