package ru.itis.habitio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(PERMIT_ALL).permitAll()
                                .requestMatchers(IGNORE).anonymous()
                                .anyRequest().permitAll()
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .cors().disable();
        return http.build();
    }
}
