package pet.declare.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super();
    }
    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserExistsException(String message) {
        super(message);
    }
    public UserExistsException(Throwable cause) {
        super(cause);
    }
}