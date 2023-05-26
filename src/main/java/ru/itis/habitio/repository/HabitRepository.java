package ru.itis.habitio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.habitio.entity.HabitEntity;

import java.util.UUID;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, UUID> {


}
