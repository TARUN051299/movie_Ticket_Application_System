package movieticket.controller;


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
public class QRCodeController {


    private final QRCodeGeneration qrCodeGeneration;

    public QRCodeController(QRCodeGeneration qrCodeGeneration) {
        this.qrCodeGeneration = qrCodeGeneration;
    }



    @GetMapping("/{qrCodeId}")

    public ResponseEntity<FileSystemResource>getQrCode(@PathVariable String qrCodeId){
        QRCodeEntity qrCode= qrCodeGeneration.getQrCode(qrCodeId);


        File file=new File(qrCode.getImagePath());

        FileSystemResource resource=new FileSystemResource(file);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }
}
