package movieticket.repository;

import movieticket.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository  extends JpaRepository<ShowEntity,Long> {
    List<ShowEntity> findByMovieId(Long id);
    ShowEntity getShowsByMovieId(Long id);
    Optional<ShowEntity> findById(Long id);
}
