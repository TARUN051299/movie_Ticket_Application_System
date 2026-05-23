package movieticket.controller;


import movieticket.Dto.LoginRequest;
import movieticket.Dto.RegisterRequest;
import movieticket.entity.UserEntity;
import movieticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")

public class UserController {

    private  final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Register successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<String> token=userService.login(loginRequest.email(), loginRequest.password());
        if (token.isEmpty()){
            return ResponseEntity.badRequest().body("Username or password is invalid");
        }
        return ResponseEntity.ok("Logged in successfully!");
    }



    @PostMapping("/logout")
    public ResponseEntity<String>logout(@RequestHeader String sessionId){
        userService.logout(sessionId);
        return ResponseEntity.ok("Logout successfully!");
    }



}
