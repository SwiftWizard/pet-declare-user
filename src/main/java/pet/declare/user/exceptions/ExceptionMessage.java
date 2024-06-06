package pet.declare.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ExceptionMessage {
    private String message;
    private ZonedDateTime serverTime;
}