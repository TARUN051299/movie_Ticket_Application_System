package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="halls")
@Getter
@Setter
public class TheatreEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private  String name;

    private String location;
}
