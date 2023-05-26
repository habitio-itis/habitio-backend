package ru.itis.habitio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.habitio.entity.UsersHabitsEntity;

import java.util.UUID;

@Repository
public interface UserHabitsRepository extends JpaRepository<UsersHabitsEntity, UUID> {
}
