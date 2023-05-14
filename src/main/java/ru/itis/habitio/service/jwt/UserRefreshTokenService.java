package ru.itis.habitio.service.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.config.ApplicationProperties;
import ru.itis.habitio.entity.UserEntity;
import ru.itis.habitio.entity.jwt.UserRefreshTokenEntity;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.repository.UserRefreshTokenRepository;
import ru.itis.habitio.repository.UserRepository;
import ru.itis.habitio.service.dto.UserRefreshTokenDto;
import ru.itis.habitio.service.mapper.UserRefreshTokenMapper;
import ru.itis.habitio.web.dto.response.UserResponse;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRefreshTokenService {

    private final UserRepository userRepository;

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final UserRefreshTokenMapper mapper;

    private final ApplicationProperties applicationProperties;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserRefreshTokenDto generateUserRefreshToken(UserResponse userResponse) {
        var refreshToken = UserRefreshTokenEntity.builder()
                .user(userRepository.findById(userResponse.getId()).orElseThrow())
                .expiryDate(ZonedDateTime.now().plus(applicationProperties.getRefreshTokenExpirationMillis(), ChronoUnit.MILLIS))
                .build();
        return mapper.toDto(userRefreshTokenRepository.save(refreshToken));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserRefreshToken(UUID id) {
        var refreshToken = userRefreshTokenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Refresh token not found"));
        userRefreshTokenRepository.delete(refreshToken);
    }
}
