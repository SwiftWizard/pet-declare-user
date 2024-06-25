package pet.declare.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/*
    * Could be enhanced with more attributes, such as reason etc.
 */

@Data
@Builder
@AllArgsConstructor
public class ExceptionDetails {
    private String message;
    private String locale;
    private LocalDateTime time;
    private String path;

}