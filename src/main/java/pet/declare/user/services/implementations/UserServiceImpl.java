package pet.declare.user.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pet.declare.user.domain.User;
import pet.declare.user.dtos.inbound.UserPost;
import pet.declare.user.dtos.outbound.AuthResponse;
import pet.declare.user.exceptions.UserExistsException;
import pet.declare.user.repository.UserRepository;
import pet.declare.user.utils.JwtUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements pet.declare.user.services.interfaces.UserService {
    private UserRepository userRepo;
    private JwtUtils jwtUtils;

    @Override
    public User save(User user){
        if(userExists(user)){
            throw new UserExistsException(String.format("User with %s already registered", user.getEmail()));
        }
        return userRepo.save(user);
    }

    @Override
    public User registerNew(UserPost userPost) {
        var newUser = User.builder()
                        .email(userPost.getEmail())
                        .password(userPost.getPassword())
                        .name(userPost.getName())
                        .surname(userPost.getSurname())
                        .address(userPost.getAddress())
                        .contactNumber(userPost.getContactNumber())
                        .emailVerified(true)
                        .lastPasswordChangedTime(LocalDateTime.now())
                        .lastActiveTime(LocalDateTime.now())
                        .role(User.DEFAULT_USER_ROLE)
                    .build();
        return save(newUser);
    }

    @Override
    public Optional<User> findById(String id){
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public boolean userExists(User user){
        return userRepo.findByEmail(user.getEmail()).isPresent();
    }
}
