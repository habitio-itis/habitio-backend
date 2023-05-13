package ru.itis.habitio.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_habits")
public class UsersHabitsEntity extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "habit_id", referencedColumnName = "id")
    private HabitEntity habit;
}
