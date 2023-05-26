package ru.itis.habitio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.habitio.entity.HabitCompletionHistory;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.repository.HabitCompletionHistoryRepository;
import ru.itis.habitio.repository.HabitRepository;
import ru.itis.habitio.repository.UserRepository;
import ru.itis.habitio.web.dto.response.UserCompletionHistoryResponse;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HabitCompletionHistoryService {

    private final HabitCompletionHistoryRepository habitCompletionHistoryRepository;

    private final UserRepository userRepository;

    private final HabitRepository habitRepository;

    @Transactional
    public void complete(UUID habitId, String username) {
        var user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        var habit = habitRepository.findById(habitId).orElseThrow(NotFoundException::new);
        var habitCompletionHistory = new HabitCompletionHistory(habit, user, ZonedDateTime.now());
        habitCompletionHistoryRepository.save(habitCompletionHistory);
    }


    public List<UserCompletionHistoryResponse> getCompletionHistory(UUID habitId, UUID userId) {
        var userCompletionHistory = habitCompletionHistoryRepository.findAllByUser_Id(userId);
        return userCompletionHistory.stream()
                .map(h -> new UserCompletionHistoryResponse(h.getCompletionTime()))
                .toList();
    }
}
