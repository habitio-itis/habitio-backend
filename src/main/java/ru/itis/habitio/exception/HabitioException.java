package ru.itis.habitio.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HabitioException extends RuntimeException {

    private final HttpStatus statusCode;

    public HabitioException(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HabitioException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HabitioException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public HabitioException(Throwable cause, HttpStatus statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public HabitioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus statusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCode = statusCode;
    }
}
