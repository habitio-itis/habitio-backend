package ru.itis.habitio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "habit")
public class HabitEntity extends AbstractAuditableEntity {

    private String name;

    private String description;

    private String icon;

    private String cron;

    @OneToMany(mappedBy = "habit")
    private List<HabitCompletionHistory> habitCompletionHistories;
}
