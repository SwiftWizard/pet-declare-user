package pet.declare.user.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.declare.user.domain.User;
import pet.declare.user.exceptions.UserExistsException;
import pet.declare.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepo;

    public User save(User user){
        if(userExists(user)){
            throw new UserExistsException(String.format("User with %s already registered", user.getEmail()));
        }
        return userRepo.save(user);
    }

    private boolean userExists(User user){
        return userRepo.findByEmail(user.getEmail()).isPresent();
    }
}
