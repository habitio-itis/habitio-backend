package ru.itis.habitio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.repository.UserRepository;
import ru.itis.habitio.service.jwt.JwtTokenService;
import ru.itis.habitio.service.mapper.UserMapper;
import ru.itis.habitio.web.dto.request.LoginRequest;
import ru.itis.habitio.web.dto.request.RefreshTokenRequest;
import ru.itis.habitio.web.dto.response.AccessRefreshTokensResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtTokenService jwtTokenService;

    @Transactional(propagation = Propagation.REQUIRED)
    public AccessRefreshTokensResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return jwtTokenService.login(userMapper.toResponse(user));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AccessRefreshTokensResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        return jwtTokenService.refreshTokens(refreshTokenRequest.getRefreshToken());
    }
}
