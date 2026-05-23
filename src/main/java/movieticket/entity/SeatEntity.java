package movieticket.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="seats")
@Getter
@Setter
public class SeatEntity {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String seatNumber;

    @ManyToOne
    @JsonIgnore
    private ShowEntity show;

    @Enumerated(EnumType.STRING)
    private SeatsStatus status;  //AVAILABLE /BOOKED
}

