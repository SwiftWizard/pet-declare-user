package pet.declare.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractUser{
    private String name;
    private String surname;
    private String profileImageUrl;
    private String contactNumber;
    private Address address;
}
