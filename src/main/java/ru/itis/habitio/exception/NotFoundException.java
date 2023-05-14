package ru.itis.habitio.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HabitioException {

    private static final HttpStatus HTTP_STATUS_CODE = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(HTTP_STATUS_CODE);
    }

    public NotFoundException(String message) {
        super(message, HTTP_STATUS_CODE);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, HTTP_STATUS_CODE);
    }

    public NotFoundException(Throwable cause) {
        super(cause, HTTP_STATUS_CODE);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, HTTP_STATUS_CODE);
    }
}
