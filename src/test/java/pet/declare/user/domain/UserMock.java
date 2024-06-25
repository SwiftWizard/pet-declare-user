package pet.declare.user.domain;

import java.time.LocalDateTime;

public class UserMock {

   public static User mockUser(){
        return mockUser("01", "Timmy", "timmy@email.com", true);
    }
   public static User mockUser(String id, String name, String email, boolean isVerified){
        var address = Address.builder()
                .city(name + "-city")
                .state(name + "-country")
                .postalCode(name + "-postCode")
                .addressLine(name + "street 7")
                .build();

        return User.builder()
                .id(id)
                .name("Jon")
                .surname("Doe")
                .email(email)
                .password("password")
                .address(address)
                .role(User.DEFAULT_USER_ROLE)
                .profileImageUrl("/pictures/pretty-userpic")
                .emailVerified(isVerified)
                .lastActiveTime(LocalDateTime.now().minusDays(3))
                .lastPasswordChangedTime(LocalDateTime.now().minusMonths(3))
                .build();
    }

}
