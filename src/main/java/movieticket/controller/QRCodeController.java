package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.entity.QRCodeEntity;
import movieticket.utills.QRCodeGeneration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/qrcode")
@Tag(name="Qr__Code Generation",description = "payment completed get Qr code")
public class QRCodeController {


    private final QRCodeGeneration qrCodeGeneration;

    public QRCodeController(QRCodeGeneration qrCodeGeneration) {
        this.qrCodeGeneration = qrCodeGeneration;
    }

    @Operation(summary = "QR__Code",description = "Getting Qr__Code  ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully ",
                    content = @Content(schema = @Schema(implementation = QRCodeEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })

    @GetMapping("/{qrCodeId}")

    public ResponseEntity<FileSystemResource>getQrCode(@PathVariable String qrCodeId){
        QRCodeEntity qrCode= qrCodeGeneration.getQrCode(qrCodeId);


        File file=new File(qrCode.getImagePath());

        FileSystemResource resource=new FileSystemResource(file);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }
}
