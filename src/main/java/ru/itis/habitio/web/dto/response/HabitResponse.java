package ru.itis.habitio.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitResponse {

    private UUID id;

    private String name;

    private String description;

    private String cron;

    private String icon;

}
