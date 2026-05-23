package movieticket.Dto;


import java.util.List;

public record BookingRequest( Long showId, List<Long> seatIds,Double totalAmount) {
}
