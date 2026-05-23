package movieticket.controller;


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




    @PostMapping
    public ResponseEntity<?> booking(@RequestHeader String session,@RequestBody BookingRequest bookingRequest) {
        SessionEntity sessionEntity =sessionRepository.findBySessionId(session);
        if(sessionEntity==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookingService.booking(bookingRequest,session));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingEntity>> getBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookings(id));

    }


    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}
