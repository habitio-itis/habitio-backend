package ru.itis.habitio.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 8, max = 32)
    @NotNull
    private String password;

    private String phone;
}
