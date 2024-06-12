package pet.declare.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.declare.user.domain.User;
import pet.declare.user.services.interfaces.UserService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("users/auth")
@AllArgsConstructor
public class CredentialsRepository {
    private UserService userService;
    @PostMapping("/new")
    public ResponseEntity<User> save(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), CREATED);
    }
}
