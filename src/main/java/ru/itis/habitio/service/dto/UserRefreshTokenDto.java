package ru.itis.habitio.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class UserRefreshTokenDto {

    private UUID id;

    private String username;

    private ZonedDateTime expiryDate;
}
