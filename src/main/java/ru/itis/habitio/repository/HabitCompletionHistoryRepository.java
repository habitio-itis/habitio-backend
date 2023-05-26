package ru.itis.habitio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.habitio.entity.HabitCompletionHistory;

import java.util.List;
import java.util.UUID;

@Repository
public interface HabitCompletionHistoryRepository extends JpaRepository<HabitCompletionHistory, UUID> {

    List<HabitCompletionHistory> findAllByUser_Id(UUID userId);
}
