package movieticket.repository;

import movieticket.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    SessionEntity findByUserId(Long userId);
    SessionEntity findBySessionId(String sessionId);
}
