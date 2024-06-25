package pet.declare.user.dtos.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.declare.user.domain.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPost {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String contactNumber;
    private Address address;
}
