package movieticket.service;


import movieticket.Dto.BookingRequest;
import movieticket.entity.*;
import movieticket.repository.BookingRepository;
import movieticket.repository.SeatRepository;
import movieticket.repository.SessionRepository;
import movieticket.repository.ShowRepository;
import movieticket.utills.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final SessionRepository sessionRepository;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, SeatRepository seatRepository, SessionRepository sessionRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.sessionRepository = sessionRepository;
    }

    public Optional<String> booking(BookingRequest bookingRequest,String sessionId) {




        // fetch All shows
        Optional<ShowEntity> showEntity = showRepository.findById(bookingRequest.showId()) ;
            if (showEntity.isEmpty()) {
                throw new RuntimeException("Show not found");
            }

         List<SeatEntity> seats=seatRepository.findAllById(bookingRequest.seatIds());

            for (SeatEntity seatEntity : seats) {
                if (seatEntity.getStatus()== SeatsStatus.BOOKED){
                    throw new RuntimeException("Seat is already booked");
                }



                seatEntity.setStatus(SeatsStatus.PENDING);
            }
            seatRepository.saveAll(seats);


            SessionEntity sessionEntity =sessionRepository.findBySessionId(sessionId);
            if (sessionEntity==null){
                throw new RuntimeException("Session not found");
            }
            UserEntity user=sessionEntity.getUser();


            BookingEntity booking = new BookingEntity();

            booking.setBookingId(IdGenerator.generateBookingId());
             booking.setUser(user);

            booking.setShow(showEntity.get());
            booking.setSeats(seats);
            booking.setBookingTime(LocalDateTime.now());
            booking.setTotalAmount(bookingRequest.totalAmount());
            booking.setStatus(BookingEntity.Status.PENDING);
            bookingRepository.save(booking);

            return Optional.of("Booked Successfully");
        }



    public List<BookingEntity>getBookings(Long id){
        return bookingRepository.findByUserId(id);
    }


    public String cancelBooking(String bookingId){
        BookingEntity booking = bookingRepository.findByBookingId(bookingId);

        if (booking==null){
            throw new RuntimeException("Booking not found");
        }

        booking.setStatus(BookingEntity.Status.CANCELLED);

        for (SeatEntity seatEntity : booking.getSeats()){
            seatEntity.setStatus(SeatsStatus.AVAILABLE);
        }
        seatRepository.saveAll(booking.getSeats());

        bookingRepository.save(booking);
        return "Booking cancelled successfully";
    }

    }






