package ru.itis.habitio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.habitio.entity.jwt.UserRefreshTokenEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, UUID> {

    Optional<UserRefreshTokenEntity> findByUserId(UUID userId);
}
