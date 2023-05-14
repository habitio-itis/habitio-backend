package ru.itis.habitio.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.itis.habitio.entity.jwt.UserRefreshTokenEntity;
import ru.itis.habitio.service.dto.UserRefreshTokenDto;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRefreshTokenMapper {


    @Mapping(target = "username", expression = "java(entity.getUser().getUsername())")
    UserRefreshTokenDto toDto(UserRefreshTokenEntity entity);
}
