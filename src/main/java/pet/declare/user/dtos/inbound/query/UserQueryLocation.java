package pet.declare.user.dtos.inbound.query;

import lombok.Data;

@Data
public class UserQueryLocation {
    private String countryCode;
    private String state;
    private String postalCode;
    private String city;
}
