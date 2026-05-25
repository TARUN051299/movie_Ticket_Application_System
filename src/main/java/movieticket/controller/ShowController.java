package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.ShowsRequest;
import movieticket.entity.MovieEntity;
import movieticket.entity.SeatEntity;
import movieticket.entity.ShowEntity;
import movieticket.repository.SeatRepository;
import movieticket.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@Tag(name = "MOVIE__Shows",description = "API's for shows of the movies")
public class ShowController {

    private final ShowService showService;

    private  final SeatRepository seatRepository;

    public ShowController(ShowService showService, SeatRepository seatRepository) {
        this.showService = showService;
        this.seatRepository = seatRepository;
    }

    @Operation(summary = "Creating Movie__Shows",description = "Creating shows of all movies ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Created successfully ",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOt Created",
                    content = @Content
            )
    })



    @PostMapping
    public ResponseEntity<?> addShow(@RequestBody ShowsRequest showsRequest) {
        String showId = showService.addShow(showsRequest);
        if (showId == null) {
            return ResponseEntity.badRequest().body("Add Show Failed");
        }
        return ResponseEntity.ok("Show Updated Successfully ");
    }

    @Operation(summary = "Get All  Show Listed",description = "GET All  list ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved ",
                    content = @Content(schema = @Schema(implementation = MovieEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOt Found",
                    content = @Content
            )
    })

    @GetMapping
    public ResponseEntity<List<ShowEntity>> getShowsByMovieId(@RequestParam Long movieId) {

        if (movieId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(showService.getShowsByMovieId(movieId));
    }


    @Operation(summary = "Shows Get id",description = "Retrieves a specific record by id ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get id ",
                    content = @Content(schema = @Schema(implementation = ShowEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<SeatEntity>> getSeats(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(seatRepository.findByShowId(id));
    }
}
