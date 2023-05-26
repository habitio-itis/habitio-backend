package ru.itis.habitio.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitResponse {

    private String name;

    private String description;

    private String cron;

    private String icon;

}
