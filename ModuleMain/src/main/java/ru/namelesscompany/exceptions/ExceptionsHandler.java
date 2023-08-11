package ru.namelesscompany.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.namelesscompany.exceptions.user.UserNotFound;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> userNotFound(final UserNotFound e) {
        log.info("Пользователь не найден. {}", e.getMessage());
        String exceptionName = e.getClass().getName();
        return new ResponseEntity<>(new ExceptionResponse(exceptionName, e.getMessage(),
                "NOT_FOUND", LocalDateTime.now().format(formatter)), HttpStatus.NOT_FOUND);
    }

    @Getter
    @AllArgsConstructor
    static class ExceptionResponse {
        private final String exception;
        private final String message;
        private final String status;
        private final String timestamp;
    }
}
