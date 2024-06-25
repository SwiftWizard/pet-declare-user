package pet.declare.user.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionDetails> handleUserExistsException(UserExistsException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> handleBadCredentialsException(BadCredentialsException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionDetails> handleLockedException(LockedException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<ExceptionDetails> handleCredentialsExpiredException(CredentialsExpiredException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionDetails> handleDisabledException(DisabledException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ExceptionDetails> handleDisabledException(InternalAuthenticationServiceException ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ExceptionDetails> handleDisabledException(HttpServerErrorException.InternalServerError ex, ServletWebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .locale(String.valueOf(request.getLocale()))
                        .path(request.getRequest().getRequestURI())
                        .message(ex.getMessage())
                        .time(LocalDateTime.now())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, ServletWebRequest request) {
        return new ResponseEntity<>(
                makeValidationErrorDetails(ex, request), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ValidationErrorDetails makeValidationErrorDetails(MethodArgumentNotValidException ex, ServletWebRequest request){
        logger.error(ex);
        var errorFields = ex.getFieldErrors().stream().map(this::parseErrorField).toList();
        var exceptionMessage = ExceptionDetails.builder()
                .locale(String.valueOf(request.getLocale()))
                .path(request.getRequest().getRequestURI())
                .message(ex.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ValidationErrorDetails(errorFields, exceptionMessage);
    }

    private ErrorField parseErrorField(FieldError fieldError){
        return new ErrorField(fieldError.getField(), fieldError.getDefaultMessage());
    }
}