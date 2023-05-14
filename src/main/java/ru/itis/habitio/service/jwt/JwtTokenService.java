package ru.itis.habitio.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.config.ApplicationProperties;
import ru.itis.habitio.service.UserService;
import ru.itis.habitio.web.dto.response.AccessRefreshTokensResponse;
import ru.itis.habitio.web.dto.response.UserResponse;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtTokenService {

    private final UserService userService;

    private final UserRefreshTokenService userRefreshTokenService;

    private final ApplicationProperties applicationProperties;

    @Transactional(propagation = Propagation.REQUIRED)
    public AccessRefreshTokensResponse login(UserResponse user) {
        return AccessRefreshTokensResponse.builder()
                .accessToken(generateAccessToken(user))
                .refreshToken(generateRefreshToken(user))
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AccessRefreshTokensResponse refreshTokens(String refreshToken) {
        var decodedJWT = verify(refreshToken);
        var user = userService.getByUsername(decodedJWT.getSubject());
        var refreshTokenId = UUID.fromString(decodedJWT.getClaim("tokenId").asString());
        userRefreshTokenService.deleteUserRefreshToken(refreshTokenId);
        return login(user);
    }

    private DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC512(applicationProperties.getJwtSecret()))
                .acceptExpiresAt(applicationProperties.getAccessTokenExpirationMillis())
                .build()
                .verify(token);
    }

    public String validateAndExtractSubject(String accessToken) {
        try {
            return JWT.require(Algorithm.HMAC512(applicationProperties.getJwtSecret()))
                    .acceptExpiresAt(applicationProperties.getAccessTokenExpirationMillis())
                    .build()
                    .verify(accessToken)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException();
        }
    }

    private String generateAccessToken(UserResponse user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(ZonedDateTime.now()
                        .plus(applicationProperties.getAccessTokenExpirationMillis(), ChronoUnit.MILLIS).toInstant())
                .sign(Algorithm.HMAC512(applicationProperties.getJwtSecret()));
    }

    private String generateRefreshToken(UserResponse user) {
        var userRefreshTokenDto = userRefreshTokenService.generateUserRefreshToken(user);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(userRefreshTokenDto.getExpiryDate().toInstant())
                .withClaim("tokenId", userRefreshTokenDto.getId().toString())
                .sign(Algorithm.HMAC512(applicationProperties.getJwtSecret()));
    }
}
