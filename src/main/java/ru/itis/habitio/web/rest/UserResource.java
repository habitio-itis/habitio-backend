package ru.itis.habitio.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.habitio.service.UserService;
import ru.itis.habitio.web.dto.request.UserRegisterRequest;
import ru.itis.habitio.web.dto.response.UserResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @PostMapping
    public UserResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }
}
