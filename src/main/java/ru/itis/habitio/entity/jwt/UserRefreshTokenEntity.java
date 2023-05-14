package ru.itis.habitio.entity.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import ru.itis.habitio.entity.AbstractAuditableEntity;
import ru.itis.habitio.entity.UserEntity;

import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_refresh_token")
public class UserRefreshTokenEntity extends AbstractAuditableEntity {

    private ZonedDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
