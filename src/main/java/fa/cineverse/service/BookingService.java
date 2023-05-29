package fa.cineverse.service;

import java.util.List;
import java.util.Optional;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Booking;

public interface BookingService {

	Booking save(Booking booking);
	
	List<Booking> findAll();

    Optional<Booking> findById(String id);

	Booking changePaymentStatus(String id);
}
