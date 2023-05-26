package ru.itis.habitio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.entity.HabitEntity;
import ru.itis.habitio.entity.UsersHabitsEntity;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.repository.HabitRepository;
import ru.itis.habitio.repository.UserHabitsRepository;
import ru.itis.habitio.repository.UserRepository;
import ru.itis.habitio.service.mapper.HabitMapper;
import ru.itis.habitio.web.dto.CreateHabitRequest;
import ru.itis.habitio.web.dto.response.HabitResponse;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitService {

    private final UserRepository userRepository;

    private final HabitRepository habitRepository;

    private final UserHabitsRepository userHabitsRepository;

    private final HabitMapper habitMapper;

    public List<HabitResponse> getUserHabits(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        return user.getUserHabits().stream()
                .map(o -> habitMapper.toResponse(o.getHabit()))
                .toList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveHabit(CreateHabitRequest createHabitRequest) {
        var habit = habitMapper.toEntity(createHabitRequest);
        habitRepository.save(habit);
    }

    @Transactional
    public void applyHabit(UUID habitId, String username) {
        var habit = habitRepository.findById(habitId).orElseThrow(NotFoundException::new);
        var user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        var userHabit = new UsersHabitsEntity(user, habit);
        userHabitsRepository.save(userHabit);
    }

    public List<HabitResponse> getHabits() {
        var all = habitRepository.findAll();
        return all.stream().map(habitMapper::toResponse).toList();
    }
}
