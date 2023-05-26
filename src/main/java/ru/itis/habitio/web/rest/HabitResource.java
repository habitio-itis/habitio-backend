package ru.itis.habitio.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.habitio.service.HabitService;
import ru.itis.habitio.util.AuthUtil;
import ru.itis.habitio.web.dto.CreateHabitRequest;
import ru.itis.habitio.web.dto.response.HabitResponse;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/habit")
public class HabitResource {

    private final HabitService habitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateHabitRequest createHabitRequest) {
        habitService.saveHabit(createHabitRequest);
    }

    @GetMapping("/apply/{habitId}")
    public void applyHabit(@PathVariable UUID habitId) {
        var username = AuthUtil.getUserDetails().getUsername();
        habitService.applyHabit(habitId, username);
    }

    @GetMapping()
    public List<HabitResponse> getUserHabits() {
        return habitService.getUserHabits(AuthUtil.getUserDetails().getUsername());
    }

    @GetMapping("/pool")
    public List<HabitResponse> getHabits() {
        return habitService.getHabits();
    }
}
