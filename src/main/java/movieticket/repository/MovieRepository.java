package movieticket.repository;

import movieticket.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    MovieEntity getMovieById(Long id);
}
