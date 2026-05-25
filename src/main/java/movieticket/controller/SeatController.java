package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.SeatRequest;
import movieticket.entity.SeatEntity;
import movieticket.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Seat_Selection",description = "API's for Seat_Selection")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @Operation(summary = "Creating a Seat",description = "Creating a new seats")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Created successfully ",
                    content = @Content(schema = @Schema(implementation = SeatEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not exist",
                    content = @Content
            )
    })
    @PostMapping("/seats")
    public ResponseEntity<?> addSeat(@RequestBody SeatRequest seatRequest) {

        String seat=seatService.addSeat(seatRequest);
        if(seat==null){
            return ResponseEntity.badRequest().body("Seat is not Available");
        }
        return ResponseEntity.ok("Seat is Available");
    }
}
