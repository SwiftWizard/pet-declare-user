package pet.declare.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.declare.user.domain.User;
import pet.declare.user.services.implementations.UserServiceImpl;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World";
    }

    @PostMapping("/new")
    public ResponseEntity<User> save(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        var user = userService.findById(id);

        if(user.isEmpty()){
            return new ResponseEntity<>(NO_CONTENT);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

}
