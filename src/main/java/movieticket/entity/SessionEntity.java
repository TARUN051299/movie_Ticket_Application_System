package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name ="sessions")
@Getter
@Setter
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String sessionId;

    @ManyToOne
    private UserEntity user;

    private LocalDateTime  startTime;
    private LocalDateTime endTime;
}
