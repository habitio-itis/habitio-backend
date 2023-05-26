package ru.itis.habitio.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHabitRequest {

    private String name;

    private String description;
}
