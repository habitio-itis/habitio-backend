package ru.itis.habitio.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    @NotNull
    private Long accessTokenExpirationMillis;

    @NotNull
    private Long refreshTokenExpirationMillis;

    @NotNull
    private String jwtSecret;
}
