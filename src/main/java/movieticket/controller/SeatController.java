package movieticket.controller;


import movieticket.Dto.SeatRequest;
import movieticket.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/seats")
    public ResponseEntity<?> addSeat(@RequestBody SeatRequest seatRequest) {

        String seat=seatService.addSeat(seatRequest);
        if(seat==null){
            return ResponseEntity.badRequest().body("Seat is not Available");
        }
        return ResponseEntity.ok("Seat is Available");
    }
}
