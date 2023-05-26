package ru.itis.habitio.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.itis.habitio.service.UserService;
import ru.itis.habitio.service.jwt.JwtTokenService;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationUserDetailsService jwtAuthenticationUserDetailsService;

    private final JwtTokenService jwtTokenService;

    private final UserService userService;

    private static final String[] PERMIT_ALL = {
            "/api/v1/auth/**",
            "/api/v1/user/**",
            "/api/v1/auth/refresh",
            "/api/v1/user"
    };

    private static final String[] IGNORE = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/webjars/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authenticationManager = authenticationManager();
        http
                .addFilterBefore(jwtAuthenticationFilter(userService, jwtTokenService, authenticationManager), RequestHeaderAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/api/v1/user/**",
                        "/api/v1/auth/refresh",
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()
                .anyRequest()
                    .permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(jwtAuthenticationUserDetailsService);
        authenticationProvider.setThrowExceptionWhenTokenRejected(false);

        return authenticationProvider;
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter(UserService userService, JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        return new JwtAuthenticationFilter(jwtTokenService, userService, authenticationManager);
    }
}
