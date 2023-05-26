package ru.itis.habitio.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.habitio.security.HabitioUserDetails;
import ru.itis.habitio.service.HabitService;
import ru.itis.habitio.util.AuthUtil;
import ru.itis.habitio.web.dto.CreateHabitRequest;
import ru.itis.habitio.web.dto.response.HabitResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/habit")
public class HabitResource {

    private final HabitService habitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateHabitRequest createHabitRequest) {
        habitService.saveHabit(createHabitRequest, AuthUtil.getUserDetails().getUsername());
    }

    @GetMapping()
    public List<HabitResponse> getUserHabits(Pageable pageable) {
        return habitService.getUserHabits(AuthUtil.getUserDetails().getUsername());
    }
}
