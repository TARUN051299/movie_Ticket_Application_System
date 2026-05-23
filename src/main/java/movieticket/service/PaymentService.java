package movieticket.service;


import com.google.zxing.WriterException;
import movieticket.Dto.PaymentRequest;
import movieticket.Dto.PaymentResponse;
import movieticket.entity.*;
import movieticket.repository.BookingRepository;
import movieticket.repository.PaymentRepository;
import movieticket.repository.SeatRepository;
import movieticket.utills.IdGenerator;
import movieticket.utills.QRCodeGeneration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private  final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final QRCodeGeneration qrCodeGeneration;
    private final SeatRepository seatRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository, QRCodeGeneration qrCodeGeneration, SeatRepository seatRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.qrCodeGeneration = qrCodeGeneration;
        this.seatRepository = seatRepository;
    }

    public PaymentResponse makePayment(PaymentRequest paymentRequest)  {
     try {


        BookingEntity booking= bookingRepository.findByBookingId(paymentRequest.bookingId());
        if (booking==null){
            throw new RuntimeException("Booking not found");
        }

         for (SeatEntity seat:booking.getSeats()){
             seat.setStatus(SeatsStatus.BOOKED);
         }
         seatRepository.saveAll(booking.getSeats());


         booking.setStatus(BookingEntity.Status.BOOKED);
         bookingRepository.save(booking);

         PaymentEntity paymentEntity = new PaymentEntity();

         paymentEntity.setPaymentId(IdGenerator.generatePaymentId());
         paymentEntity.setBooking(booking);
         paymentEntity.setAmount(paymentRequest.amount());

         paymentEntity.setPaymentStatus("SUCCESS");

         paymentEntity.setPaymentTime(LocalDateTime.now());


         PaymentEntity payment = paymentRepository.save(paymentEntity);





         //QR TEXT
         String qrText = "payment ID :"
                 + payment.getPaymentId()
                 + "Amount :"
                 + payment.getAmount()
                 + "PaymentStatus :"
                 + payment.getPaymentStatus();


         //GENERATE QR CODE

         QRCodeEntity qrCode =
                 qrCodeGeneration.generateQRCode(qrText);

         paymentEntity.setQrCode(qrCode);
         PaymentEntity updatedPayment=paymentRepository.save(paymentEntity);

         return new PaymentResponse(
                 updatedPayment.getPaymentId(),
                 updatedPayment.getAmount(),
                 updatedPayment.getPaymentStatus(),
                 updatedPayment.getPaymentTime(),
                updatedPayment.getQrCode().getQrCodeId(),
                 updatedPayment.getQrCode().getImagePath()

         );

     } catch (RuntimeException e) {
         throw new RuntimeException(e.getMessage());

     }


    }


    public PaymentResponse getPayment(String paymentId) {

        PaymentEntity paymentEntity= paymentRepository.findByPaymentId(paymentId);
        if (paymentEntity==null){
            throw new RuntimeException("Payment not found");
        }





        return new PaymentResponse(
                paymentEntity.getPaymentId(),
                paymentEntity.getAmount(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getPaymentTime(),
                paymentEntity.getQrCode().getQrCodeId(),
                paymentEntity.getQrCode().getImagePath()

                );

    }
}

