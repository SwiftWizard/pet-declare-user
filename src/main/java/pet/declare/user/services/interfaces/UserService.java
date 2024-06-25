package pet.declare.user.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import pet.declare.user.domain.User;
import pet.declare.user.dtos.inbound.AuthRequest;
import pet.declare.user.dtos.inbound.UserPost;
import pet.declare.user.dtos.outbound.AuthResponse;

import java.util.Optional;

public interface UserService {
    User save(User user);

    User registerNew(UserPost userPost);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean userExists(User user);
}
