package movieticket.controller;


import movieticket.Dto.PaymentRequest;
import movieticket.Dto.PaymentResponse;
import movieticket.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }


    // MAKE PAYMENT
    @PostMapping
    public ResponseEntity<?> makePayment(
            @RequestBody PaymentRequest paymentRequest
    ) {

        try {

            PaymentResponse response =
                    paymentService.makePayment(
                            paymentRequest
                    );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


    // GET PAYMENT DETAILS
    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPayment(
            @PathVariable String paymentId
    ) {

        try {

            PaymentResponse response =
                    paymentService.getPayment(paymentId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
