package movieticket.service;

import movieticket.Dto.MovieRequest;
import movieticket.entity.MovieEntity;
import movieticket.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public String addMovie(MovieRequest movieRequest) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movieRequest.title());
        movieEntity.setLanguage(movieRequest.language());
        movieEntity.setDuration(movieRequest.duration());
        movieEntity.setGenre(movieRequest.genre());
        movieRepository.save(movieEntity);

        return "movie updated successfully!";
    }

    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    public MovieEntity getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("movie not found!"));
    }
}
