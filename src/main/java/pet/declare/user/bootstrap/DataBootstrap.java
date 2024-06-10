package pet.declare.user.bootstrap;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pet.declare.user.domain.Address;
import pet.declare.user.domain.User;
import pet.declare.user.exceptions.UserExistsException;
import pet.declare.user.services.implementations.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

@Component
@Profile("data-init")
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserServiceImpl userService;
    private final String IMAGE_REPOSITORY = "https://bogus-image-repository/images/%s";

    private static String[] EMAIL_PROVIDERS = {
                        "gmail.com",
                        "outlook.com",
                        "yahoo.com",
                        "protonmail.com",
                        "email.me",
                        "mail.com"  };
    public static final int AMOUNT_USERS = 2024;
    private final Locale LOCALE= new Locale("en-US");
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveValidAccounts();
    }
    private void saveValidAccounts(){
        var users = generateRandomUsersOfLocale(LOCALE, AMOUNT_USERS);

        for (User curr : users) {
            try {
                userService.save(curr);
            }catch (UserExistsException ignored){}
        };
    }
    private List<User> generateRandomUsersOfLocale(Locale locale, int amount){
        Faker faker = new Faker(locale);

        var result = new ArrayList<User>();
        for(int i = 0; i < amount; i++){
            result.add(generateRandomUser(faker));
        }

        return result;
    }
    private User generateRandomUser(Faker faker){
        var randomActiveTime = LocalDateTime.now().minusDays(faker.random().nextInt(0,777));
        var randomPwdResetTime = LocalDateTime.now().minusDays(faker.random().nextInt(0, 1333));
        var randomSurname = (faker.random().nextBoolean())? faker.name().lastName() : null;
        var randomNumber = (faker.random().nextDouble() > 0.7)? faker.phoneNumber().cellPhone() : null;

        return User.builder()
                .email(generateRandomEmail(faker))
                .password(faker.bothify("pass###word#??"))
                .role(User.DEFAULT_USER_ROLE)
                .emailVerified(true)
                .lastActiveTime(randomActiveTime)
                .lastPasswordChangedTime(randomPwdResetTime)
                .address(generateRandomAddress(faker))
                .contactNumber(randomNumber)
                .profileImageUrl(String.format(IMAGE_REPOSITORY, UUID.randomUUID()))
                .name(faker.name().firstName())
                .surname(randomSurname)
                .build();

    }

    private String generateRandomEmail(Faker faker){
        String mailProvider = EMAIL_PROVIDERS[faker.random().nextInt(EMAIL_PROVIDERS.length)];
        String mailHolder = randomMailHolder(faker);
        int randomNumber = faker.random().nextInt(0, 1000);
        return String.format("%s%d@%s", mailHolder, randomNumber, mailProvider);
    }

    private String randomMailHolder(Faker faker){
        int rnd = faker.random().nextInt(3);
        String winner = "mclovin";
        switch (rnd){
            case 0:
                winner = faker.lordOfTheRings().character().toLowerCase().replace(" ", ".");
                break;
            case 1:
                winner = faker.starWars().character().toLowerCase().replace(" ", ".");
                break;
            case 2:
                winner = faker.breakingBad().character().toLowerCase().replace(" ", ".");
                break;
        }
        return winner;
    }

    private Address generateRandomAddress(Faker faker){
        net.datafaker.providers.base.Address address = faker.address();
        var street = faker.random().nextBoolean()? address.streetAddress() : null;
        var otherStreet = (faker.random().nextDouble() > 0.9)? address.secondaryAddress() : null;

        return Address.builder()
                        .countryCode(address.countryCode())
                        .state(address.state())
                        .postalCode(address.postcode())
                        .city(faker.address().city())
                        .addressLine(street)
                        .addressLine(otherStreet)
                        .build();
    }
}
