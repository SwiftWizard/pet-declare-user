package pet.declare.user.exceptions;

import jakarta.servlet.RequestDispatcher;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/*
    * Could be enhanced with more attributes, such as reason etc.
 */

@Data
@AllArgsConstructor
@Component
public class CustomErrorAttributes  {
    private String content;

    DefaultErrorAttributes defaultErrorAttributes;

    public DefaultErrorAttributes getDefaultErrorAttributes() {
        return defaultErrorAttributes;
    }
}