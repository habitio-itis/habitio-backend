package ru.itis.habitio.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.habitio.entity.UserEntity;
import ru.itis.habitio.web.dto.response.UserResponse;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse toResponse(UserEntity entity);
}
