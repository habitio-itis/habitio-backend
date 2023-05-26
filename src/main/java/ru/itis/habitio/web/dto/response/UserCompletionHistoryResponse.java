package ru.itis.habitio.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompletionHistoryResponse {

    private ZonedDateTime completionTime;
}
