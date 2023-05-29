package fa.cineverse.repository;

import fa.cineverse.model.ScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:35:57 PM
     * @Return: List<Ticket>
     */
    List<Ticket> findTicketByScheduleScheduleId(ScheduleId scheduleId);

    List<Ticket> findTicketByBooking_BookingId(String booking_bookingId);
}
