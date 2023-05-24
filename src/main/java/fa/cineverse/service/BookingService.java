package fa.cineverse.service;

import java.util.List;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Booking;

public interface BookingService {

	Booking save(Booking booking, List<TicketDTO> ticketDTOList);
	
	List<Booking> findAll();
	
}
