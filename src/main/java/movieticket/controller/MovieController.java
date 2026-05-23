package movieticket.controller;

import movieticket.Dto.MovieRequest;
import movieticket.entity.MovieEntity;
import movieticket.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")

public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping
    public ResponseEntity<?>addMovie(@RequestBody MovieRequest movieRequest) {

        String title=movieService.addMovie(movieRequest);
        if(title==null){
            return ResponseEntity.badRequest().body("Movie not found");
        }

        return ResponseEntity.ok("Movie Updated successfully!");
    }


    @GetMapping
    public  ResponseEntity<?> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }
}
