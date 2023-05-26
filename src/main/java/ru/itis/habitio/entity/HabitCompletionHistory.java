package ru.itis.habitio.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "habit_completion_history")
public class HabitCompletionHistory extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "habit_id", referencedColumnName = "id")
    private HabitEntity habit;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private ZonedDateTime completionTime;
}
