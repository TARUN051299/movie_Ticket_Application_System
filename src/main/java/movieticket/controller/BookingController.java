package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.BookingRequest;
import movieticket.entity.BookingEntity;

import movieticket.entity.SessionEntity;
import movieticket.repository.SessionRepository;
import movieticket.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@Tag(name = "Booking Tickets",description = "Booking tickets and seat checking")
public class BookingController {

    private final BookingService bookingService;
    private final SessionRepository sessionRepository;

    public BookingController(BookingService bookingService, SessionRepository sessionRepository) {
        this.bookingService = bookingService;
        this.sessionRepository = sessionRepository;
    }

@Operation(summary = "Booking Process",description = "After selecting Seats and Booking conformation")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Booking successfully",
        content = @Content(schema = @Schema(implementation = BookingEntity.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Booking Failed",
                content = @Content
        )
})


    @PostMapping
    public ResponseEntity<?> booking(@RequestHeader String session,@RequestBody BookingRequest bookingRequest) {
        SessionEntity sessionEntity =sessionRepository.findBySessionId(session);
        if(sessionEntity==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookingService.booking(bookingRequest,session));
    }

    @Operation(summary = "Booking details Get id",description = "Retries a booking id on display")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get id",
                    content = @Content(schema = @Schema(implementation = BookingEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Id not valid & give correct id",
                    content = @Content
            )
    })

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingEntity>> getBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookings(id));

    }
    @Operation(summary = "Booking cancel ",description = "users send a booking cancel msg")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully cancel",
                    content = @Content(schema = @Schema(implementation = BookingEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Id not valid",
                    content = @Content
            )
    })



    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}
