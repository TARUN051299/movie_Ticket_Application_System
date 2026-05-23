package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="bookings")
@Getter
@Setter

public class BookingEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @Column(unique = true)
    private String bookingId;


    @ManyToOne
    private UserEntity user;


    @ManyToOne

    private ShowEntity show;

    private Double totalAmount;

    private LocalDateTime bookingTime;

    @ManyToMany
    private List<SeatEntity> seats;

    @Enumerated(EnumType.STRING)
    private Status status=Status.PENDING;




    public enum Status{
        PENDING,BOOKED,CANCELLED
    }

}
