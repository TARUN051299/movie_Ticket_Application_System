package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="shows")
@Getter
@Setter
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @ManyToOne
    private MovieEntity movie;

    @ManyToOne
    private TheatreEntity theatre;


    private LocalDateTime showTime;

    private  Double price;


}
