package ru.itis.habitio.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class AccessRefreshTokensResponse {

    private String accessToken;

    private String refreshToken;
}
