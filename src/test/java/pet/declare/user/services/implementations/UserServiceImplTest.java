package pet.declare.user.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pet.declare.user.domain.AbstractUser;
import pet.declare.user.domain.Address;
import pet.declare.user.domain.User;
import pet.declare.user.repository.UserRepository;
import pet.declare.user.services.interfaces.UserService;
import pet.declare.user.utils.JwtUtils;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends AbstractUser {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtils jwtUtils;

    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        jwtUtils = new JwtUtils();
        userService = new UserServiceImpl(userRepository, jwtUtils);
    }
}