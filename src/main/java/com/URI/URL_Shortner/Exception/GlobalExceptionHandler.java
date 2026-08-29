package com.URI.URL_Shortner.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(DomainAlreadyExistsException.class)
    public ResponseEntity<ErrorResoponse> handleDomainExits(DomainAlreadyExistsException ex){
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resoponse);
    }


    @ExceptionHandler(DomainNotAllowedException.class)
    public ResponseEntity<ErrorResoponse> handleIllegalArgumentException(Exception ex){
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resoponse);
    }


    @ExceptionHandler(AliasAlreadyTakenException.class)
    public ResponseEntity<ErrorResoponse> handleException(Exception ex){
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resoponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResoponse> handleUserNotFoundException(Exception ex, WebRequest request){
        log.error("User not found exception: {}", ex.getMessage());
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resoponse);

    }
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorResoponse> handleRateLimitExceeption(Exception ex,WebRequest request){
        log.error("Rate limit exceeded exception: {}", ex.getMessage());
        ErrorResoponse response=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.TOO_MANY_REQUESTS.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<ErrorResoponse> handleAccountLockedException(Exception ex){
        log.error("Account locked exception: {}", ex.getMessage());
        ErrorResoponse response=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.LOCKED.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.LOCKED).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResoponse> handleBadRequestException(Exception ex){
        ErrorResoponse resoponse=ErrorResoponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resoponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResoponse> handleUnauthorizedException(Exception ex){
        ErrorResoponse resoponse=ErrorResoponse
                .builder()
                .message(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now().toLocalDate())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resoponse);
    }
}
