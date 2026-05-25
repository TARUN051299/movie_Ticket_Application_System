package movieticket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.MovieRequest;
import movieticket.entity.MovieEntity;
import movieticket.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@Tag(name="Movie searching",description="API's for Movies Title projected")

public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @Operation(summary = "Movie All Listed",description = "Added All movies list ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Imported ",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "All ready Added movie",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<?>addMovie(@RequestBody MovieRequest movieRequest) {

        String title=movieService.addMovie(movieRequest);
        if(title==null){
            return ResponseEntity.badRequest().body("Movie not found");
        }

        return ResponseEntity.ok("Movie Updated successfully!");
    }

    @Operation(summary = "Movie Get All List",description = "Retries All movies list ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved ",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content
            )
    })


    @GetMapping
    public  ResponseEntity<?> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }


    @Operation(summary = "Movie Get id",description = "Retries Get id movies list ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get Id",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ID Not Found",
                    content = @Content
            )
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }
}
