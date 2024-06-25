package pet.declare.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorDetails {
    private List<ErrorField> failedValidationFields;
    private ExceptionDetails exceptionMessage;
}