package movieticket.service;


import movieticket.Dto.SeatRequest;
import movieticket.entity.SeatEntity;
import movieticket.entity.SeatsStatus;
import movieticket.entity.ShowEntity;
import movieticket.repository.SeatRepository;
import movieticket.repository.ShowRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {


    private SeatRepository seatRepository;

    private ShowRepository showRepository;
    public SeatService(SeatRepository seatRepository, ShowRepository showRepository) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public String addSeat(SeatRequest seatRequest) {
        ShowEntity showEntity = showRepository.findById(seatRequest.showId())
                .orElseThrow(()-> new RuntimeException("Show not found"));


        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setSeatNumber(seatRequest.seatNumber());
        seatEntity.setStatus(SeatsStatus.AVAILABLE);
        seatEntity.setShow(showEntity);
        seatRepository.save(seatEntity);
        return "Seats are updated Successfully";
    }
}
