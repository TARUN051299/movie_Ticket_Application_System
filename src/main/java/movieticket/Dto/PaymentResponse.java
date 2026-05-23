package movieticket.Dto;

import java.time.LocalDateTime;

public record PaymentResponse(String paymentId, Double amount, String paymentStatus, LocalDateTime paymentDateTime,String qrCodeId,String qrCodeImagePath) {
}
