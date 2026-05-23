package movieticket.service;


import movieticket.Dto.TheatreRequest;
import movieticket.entity.TheatreEntity;
import movieticket.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public String addTheatre(TheatreRequest theatreRequest) {
        TheatreEntity theatreEntity = new TheatreEntity();
        theatreEntity.setName(theatreRequest.name());
        theatreEntity.setLocation(theatreRequest.location());
        theatreRepository.save(theatreEntity);
        return theatreEntity.getName();
    }


    public List<TheatreEntity> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public TheatreEntity getTheatre(Long id){
        return theatreRepository.findById(id).orElseThrow(()-> new RuntimeException("theatre get successfully"));
    }
}
