package movieticket.Dto;


import movieticket.entity.MovieEntity;
import movieticket.entity.TheatreEntity;

import java.time.LocalDateTime;

public record ShowsRequest(Long movieId, Long theatreId, LocalDateTime showTime, Double price) {
}