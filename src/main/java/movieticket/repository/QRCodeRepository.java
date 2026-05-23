package movieticket.repository;

import com.google.zxing.qrcode.encoder.QRCode;
import movieticket.entity.QRCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository extends JpaRepository<QRCodeEntity, Long> {

    QRCodeEntity findByQrCodeId(String qrCodeId);
}
