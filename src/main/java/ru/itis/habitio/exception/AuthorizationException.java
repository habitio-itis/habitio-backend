package ru.itis.habitio.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends HabitioException {

    private static HttpStatus STATUS = HttpStatus.UNAUTHORIZED;
    public AuthorizationException() {
        super(STATUS);
    }

    public AuthorizationException(String message) {
        super(message, STATUS);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause, STATUS);
    }

    public AuthorizationException(Throwable cause) {
        super(cause, STATUS);
    }

    public AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, STATUS);
    }
}
