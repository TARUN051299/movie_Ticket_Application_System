package movieticket.service;


import movieticket.Dto.ShowsRequest;
import movieticket.entity.MovieEntity;
import movieticket.entity.ShowEntity;
import movieticket.entity.TheatreEntity;
import movieticket.repository.MovieRepository;
import movieticket.repository.ShowRepository;
import movieticket.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }


    public String addShow(ShowsRequest showsRequest) {
        MovieEntity movie= movieRepository.findById(showsRequest.movieId())
                .orElseThrow(()-> new RuntimeException("Movie not Found"));

        TheatreEntity theatre = theatreRepository.findById(showsRequest.theatreId())
                .orElseThrow(()-> new RuntimeException("Theatre not Found"));

        ShowEntity showEntity = new ShowEntity();
        showEntity.setMovie(movie);
        showEntity.setTheatre(theatre);
        showEntity.setShowTime(showsRequest.showTime());
        showEntity.setPrice(showsRequest.price());
        showRepository.save(showEntity);
        return "Show Added Successfully";


    }
    // show ALL Movie
    public List<ShowEntity> getShowsByMovieId (Long Id) {
        return showRepository.findByMovieId(Id);
    }


}
