package movieticket.utills;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import movieticket.entity.QRCodeEntity;
import movieticket.repository.QRCodeRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class QRCodeGeneration {

    private final QRCodeRepository qrCodeRepository;

    public QRCodeGeneration(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }


        public QRCodeEntity generateQRCode(String text)
                {
           try {

             String qrcodeId=IdGenerator.qrcode();

             String folderPath="qr-images/";

               File folder=new File(folderPath);

               if(!folder.exists()){
               folder.mkdir();}

               String imagePath=folderPath+qrcodeId+".png";

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    250,
                    250
            );

               Path path =new File(imagePath).toPath();
               MatrixToImageWriter.writeToPath(
                       bitMatrix,
                       "PNG",
                       path

               );




               QRCodeEntity qrCodeEntity = new QRCodeEntity();

               qrCodeEntity.setQrCodeId(qrcodeId);
               qrCodeEntity.setImagePath(imagePath);
               qrCodeEntity.setCreatedDate(LocalDateTime.now());

               return qrCodeRepository.save(qrCodeEntity);


        }catch (Exception e){
               throw new RuntimeException(e.getMessage());
           }
    }


    public QRCodeEntity getQrCode(String qrCodeId){
        return qrCodeRepository.findByQrCodeId(qrCodeId);
    }
}




