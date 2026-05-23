package movieticket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="payments")
@Getter
@Setter
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String paymentId;

    private Double amount;

    private String paymentStatus;

    private LocalDateTime paymentTime;

  @OneToOne
    private BookingEntity booking;


  @OneToOne
    private QRCodeEntity qrCode;
}
