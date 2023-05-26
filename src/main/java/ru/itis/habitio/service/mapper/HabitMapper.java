package ru.itis.habitio.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.habitio.entity.HabitEntity;
import ru.itis.habitio.web.dto.CreateHabitRequest;
import ru.itis.habitio.web.dto.response.HabitResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HabitMapper {

    HabitResponse toResponse(HabitEntity entity);

    HabitEntity toEntity(CreateHabitRequest request);
}
