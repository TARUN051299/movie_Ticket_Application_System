package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="qrcodes")
@Getter
@Setter
public class QRCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String qrCodeId;

    private String imagePath;

    private LocalDateTime createdDate;
}
