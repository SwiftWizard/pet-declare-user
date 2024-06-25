package pet.declare.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorField {
    private String fieldName;
    private String errorMessage;
}