package movieticket.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class IdGenerator {

    public static String generateBookingId(){

        return "BOOK-"+ LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }


    public static String generatePaymentId(){

        return "PAY-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    public static String qrcode(){
        return "QRCODE-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
}
