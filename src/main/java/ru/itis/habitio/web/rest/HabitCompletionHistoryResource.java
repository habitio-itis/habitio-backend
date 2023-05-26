package ru.itis.habitio.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.habitio.service.HabitCompletionHistoryService;
import ru.itis.habitio.util.AuthUtil;
import ru.itis.habitio.web.dto.response.UserCompletionHistoryResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/completionHistory")
public class HabitCompletionHistoryResource {

    private final HabitCompletionHistoryService habitCompletionHistoryService;

    @GetMapping("/{habitId}")
    public List<UserCompletionHistoryResponse> history(@PathVariable UUID habitId) {
        return habitCompletionHistoryService.getCompletionHistory(habitId, AuthUtil.getUserDetails().getId());
    }

    @GetMapping("/complete/{habitId}")
    public void complete(@PathVariable UUID habitId) {
        habitCompletionHistoryService.complete(habitId, AuthUtil.getUserDetails().getUsername());
    }
}
