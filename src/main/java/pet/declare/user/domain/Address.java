package pet.declare.user.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Set;

@Data
@Builder
public class Address {
    @NotBlank(message = "Country required")
    private String country;
    private String countryCode;
    private String state;
    private String postalCode;
    @NotBlank(message = "City required")
    private String city;
    @Singular
    private Set<String> addressLines;
}
