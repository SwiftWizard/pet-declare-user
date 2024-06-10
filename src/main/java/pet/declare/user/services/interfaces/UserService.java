package pet.declare.user.services.interfaces;

import pet.declare.user.domain.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean userExists(User user);
}
