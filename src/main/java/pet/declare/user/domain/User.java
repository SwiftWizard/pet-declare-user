package pet.declare.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
@Persistent
@TypeAlias("user")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractUser{

    @Transient
    public static String DEFAULT_USER_ROLE = "STANDARD";

    private String name;
    private String surname;
    private String profileImageUrl;
    private String userUrl;
    private String contactNumber;
    private Address address;
}
