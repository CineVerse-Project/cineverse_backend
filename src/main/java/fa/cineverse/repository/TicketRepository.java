package fa.cineverse.repository;

import fa.cineverse.model.ScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    Ticket findTicketByScheduleScheduleIdAndSeat_SeatRoomId(ScheduleId scheduleId, String seatRoomId);
}
