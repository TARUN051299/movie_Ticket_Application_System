package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="movies")
@Getter
@Setter
public class MovieEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;

    private String language;

    private int duration;


    private  String genre;  //thriller,comedy,action

}
