package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.ScheduleId;
import fa.cineverse.model.Ticket;

public interface TicketService {

	List<Ticket> findAll();

	Ticket findTicketByScheduleScheduleIdAndSeat_SeatRoomId(ScheduleId scheduleId, String seatRoomId);

	Ticket findById(String id);

}
