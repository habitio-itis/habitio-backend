package ru.itis.habitio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.entity.UserEntity;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.repository.UserRepository;
import ru.itis.habitio.service.mapper.UserMapper;
import ru.itis.habitio.web.dto.request.LoginRequest;
import ru.itis.habitio.web.dto.request.UserRegisterRequest;
import ru.itis.habitio.web.dto.response.UserResponse;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponse getByUsername(String username) {
        return mapper.toResponse(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        var userEntity = UserEntity.builder()
                .email(userRegisterRequest.getEmail())
                .username(userRegisterRequest.getUsername())
                .phone(userRegisterRequest.getPhone())
                .passwordHash(passwordEncoder.encode(userRegisterRequest.getPassword()).getBytes(StandardCharsets.UTF_8))
                .build();
        return mapper.toResponse(userRepository.save(userEntity));
    }

}
