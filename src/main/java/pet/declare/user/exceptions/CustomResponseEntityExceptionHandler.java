package pet.declare.user.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pet.declare.user.utils.TimeUtils;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${server.timezone}")
    private String SERVER_TIME_ZONE;
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionMessage> handleUserExistsException(UserExistsException ex) {
        return new ResponseEntity<ExceptionMessage>(
                new ExceptionMessage(ex.getMessage(), TimeUtils.currentServerZonedTime()),
                HttpStatus.CONFLICT
        );
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(
                makeValidationErrorDetails(ex, request), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ValidationErrorDetails makeValidationErrorDetails(MethodArgumentNotValidException ex, WebRequest webReq){
        var errorFields = ex.getFieldErrors().stream().map(this::parseErrorField).toList();
        var atURI = ((ServletWebRequest)webReq).getRequest().getRequestURI();
        var exceptionMessage = new ExceptionMessage(ex.getMessage(), TimeUtils.currentServerZonedTime());

        return new ValidationErrorDetails(atURI, errorFields, exceptionMessage);
    }

    private ErrorField parseErrorField(FieldError fieldError){
        return new ErrorField(fieldError.getField(), fieldError.getDefaultMessage());
    }
}