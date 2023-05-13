package ru.itis.habitio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
