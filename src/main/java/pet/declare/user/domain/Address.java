package pet.declare.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Address {
    private String country;
    private String countryCode;
    private String state;
    private String postalCode;
    private String city;
    @Singular
    private Set<String> addressLines;
}
