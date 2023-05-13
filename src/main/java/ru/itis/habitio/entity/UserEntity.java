package ru.itis.habitio.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditableEntity {

    private String username;

    private String email;

    private String phone;

    @Column(name = "password_hash")
    private byte[] passwordHash;

    @Column(name = "attachment_metadata_id")
    private UUID attachmentMetadataId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UsersHabitsEntity> userHabits;
}
