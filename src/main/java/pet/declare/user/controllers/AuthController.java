package pet.declare.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.declare.user.domain.User;
import pet.declare.user.dtos.inbound.AuthRequest;
import pet.declare.user.dtos.inbound.UserPost;
import pet.declare.user.dtos.outbound.AuthResponse;
import pet.declare.user.services.interfaces.UserService;
import pet.declare.user.utils.JwtUtils;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;;
    private JwtUtils jwtUtils;
    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody UserPost user){
        return new ResponseEntity<>(userService.registerNew(user), CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        var response = jwtUtils.generateAuthResponse((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(response);
    }
}
