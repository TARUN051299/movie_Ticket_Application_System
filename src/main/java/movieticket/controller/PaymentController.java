package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.PaymentRequest;
import movieticket.Dto.PaymentResponse;
import movieticket.entity.PaymentEntity;
import movieticket.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "payment process",description = "Booking a ticket go to Payment option  ")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }
    @Operation(summary = "Make Payment",description = "payment using booking Id ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "payment Successfully ",
                    content = @Content(schema = @Schema(implementation = PaymentEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "payment Failed",
                    content = @Content
            )
    })

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
    @Operation(summary = "Get payment id",description = "Retrieves a specific payment History ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully payment details ",
                    content = @Content(schema = @Schema(implementation = PaymentEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "payment id Not Found",
                    content = @Content
            )
    })


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
