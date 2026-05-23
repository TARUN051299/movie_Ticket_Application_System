package movieticket.service;

import movieticket.Dto.RegisterRequest;
import movieticket.entity.SessionEntity;
import movieticket.entity.UserEntity;
import movieticket.repository.SessionRepository;
import movieticket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    public UserService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void register(RegisterRequest registerRequest) {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(registerRequest.username());
        userEntity.setPassword(registerRequest.password());
        userEntity.setEmail(registerRequest.email());

        userRepository.save(userEntity);

    }

    public Optional<String> login(String email, String password) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }

        UserEntity userEntity1 = userEntity.get();
        if (userEntity1.getEmail().equals(email) && userEntity1.getPassword().equals(password)) {


            String token = UUID.randomUUID().toString().replaceAll("-", "");

            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionId(token);
            sessionEntity.setUser(userEntity1);
            sessionEntity.setUsername(userEntity1.getUsername());
            sessionEntity.setStartTime(LocalDateTime.now());
            sessionEntity.setEndTime(LocalDateTime.now().plusHours(24));
            sessionRepository.save(sessionEntity);


            return Optional.of(token);
        }
        return Optional.of("Logged in successfully!");
    }


    public void logout(String sessionId){
        SessionEntity sessionEntity = sessionRepository.findBySessionId(sessionId);
        if(sessionEntity != null){
            sessionRepository.delete(sessionEntity);

        }
    }


    public UserEntity validateSession(String sessionId){
        SessionEntity sessionEntity = sessionRepository.findBySessionId(sessionId);

        if(sessionEntity ==null){
            return null;}

        if(sessionEntity.getEndTime().isBefore(LocalDateTime.now())){
            sessionRepository.delete(sessionEntity);
            return null;
        }
        return  sessionEntity.getUser();
    }



}
