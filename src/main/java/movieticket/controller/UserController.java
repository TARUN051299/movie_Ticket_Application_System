package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.LoginRequest;
import movieticket.Dto.RegisterRequest;
import movieticket.entity.MovieEntity;
import movieticket.entity.UserEntity;
import movieticket.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name="Movie Ticket Application users",description = "User information details")

public class UserController {

    private  final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "REGISTER Here",description = "API's create a new Account ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User register Successfully ",
                    content = @Content(schema = @Schema(implementation = UserEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User may already exist",
                    content = @Content
            )
    })

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Login User",description = "API's Login and stored in database ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logged Successfully",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found",
                    content = @Content
            )
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<String> token=userService.login(loginRequest.email(), loginRequest.password());
        if (token.isEmpty()){
            return ResponseEntity.badRequest().body("Username or password is invalid");
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "LogOut User",description = "API's for managing User logout")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logout successfully ",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })


    @PostMapping("/logout")
    public ResponseEntity<String>logout(@RequestHeader String sessionId){
        userService.logout(sessionId);
        return ResponseEntity.ok().build();
    }



}
