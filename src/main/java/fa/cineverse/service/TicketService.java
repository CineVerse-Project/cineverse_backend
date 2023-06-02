package fa.cineverse.service;

import java.util.List;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.model.Ticket;

public interface TicketService {

	List<Ticket> findAll();

	Ticket findById(String id);

	List<Ticket> findTicketByScheduleScheduleId(ScheduleId scheduleId);

    List<Ticket> findTicketByBookingId(String bookingId);

	void save(List<TicketDTO> ticketDTOList, String bookingId);

}
