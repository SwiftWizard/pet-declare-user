package pet.declare.user.services;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pet.declare.user.domain.User;
import pet.declare.user.exceptions.UserExistsException;
import pet.declare.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> findById(String id){
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    private boolean userExists(User user){
        return userRepo.findByEmail(user.getEmail()).isPresent();
    }
}
