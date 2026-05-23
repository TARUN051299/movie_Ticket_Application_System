package movieticket.controller;


import movieticket.Dto.ShowsRequest;
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
public class ShowController {

    private final ShowService showService;

    private  final SeatRepository seatRepository;

    public ShowController(ShowService showService, SeatRepository seatRepository) {
        this.showService = showService;
        this.seatRepository = seatRepository;
    }



    @PostMapping
    public ResponseEntity<?> addShow(@RequestBody ShowsRequest showsRequest) {
        String showId = showService.addShow(showsRequest);
        if (showId == null) {
            return ResponseEntity.badRequest().body("Add Show Failed");
        }
        return ResponseEntity.ok("Show Updated Successfully ");
    }

    @GetMapping
    public ResponseEntity<List<ShowEntity>> getShowsByMovieId(@RequestParam Long movieId) {

        if (movieId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(showService.getShowsByMovieId(movieId));
    }


    @GetMapping("/{id}/seats")
    public ResponseEntity<List<SeatEntity>> getSeats(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(seatRepository.findByShowId(id));
    }
}
