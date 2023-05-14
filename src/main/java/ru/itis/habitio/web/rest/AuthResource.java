package ru.itis.habitio.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.habitio.service.AuthService;
import ru.itis.habitio.web.dto.request.LoginRequest;
import ru.itis.habitio.web.dto.request.RefreshTokenRequest;
import ru.itis.habitio.web.dto.response.AccessRefreshTokensResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {

    public final AuthService authService;

    @PostMapping
    public AccessRefreshTokensResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping(path = "/refresh")
    public AccessRefreshTokensResponse refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refresh(refreshTokenRequest);
    }
}
