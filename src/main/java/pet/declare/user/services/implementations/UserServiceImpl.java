package pet.declare.user.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.declare.user.domain.User;
import pet.declare.user.exceptions.UserExistsException;
import pet.declare.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements pet.declare.user.services.interfaces.UserService {
    private UserRepository userRepo;

    @Override
    public User save(User user){
        if(userExists(user)){
            throw new UserExistsException(String.format("User with %s already registered", user.getEmail()));
        }
        return userRepo.save(user);
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
