package pet.declare.user.dtos.inbound.query;

import lombok.Data;

@Data
public class UserQueryInfo {
    private String name;
    private String surname;
    private String email;
}
