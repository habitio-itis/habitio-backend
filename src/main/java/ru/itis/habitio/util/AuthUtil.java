package ru.itis.habitio.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.habitio.exception.AuthorizationException;
import ru.itis.habitio.security.HabitioUserDetails;
import ru.itis.habitio.web.dto.response.UserResponse;

@UtilityClass
public class AuthUtil {

    public static UserResponse getUserDetails() {
        var context = SecurityContextHolder.getContext();
        if (context.getAuthentication().isAuthenticated()) {
            return (UserResponse) context.getAuthentication().getPrincipal();
        }
        throw new AuthorizationException("Token is not provided");
    }
}
