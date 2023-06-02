package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Booking;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Ticket;
import fa.cineverse.repository.TicketRepository;
import fa.cineverse.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> findTicketByScheduleScheduleId(ScheduleId scheduleId) {
		return ticketRepository.findTicketByScheduleScheduleId(scheduleId);
	}

	@Override
	public List<Ticket> findTicketByBookingId(String bookingId) {
		return ticketRepository.findTicketByBooking_BookingId(bookingId);
	}

	@Override
	public void save(List<TicketDTO> ticketDTOList, String bookingId) {
		Booking booking = bookingRepository.findById(bookingId).orElse(null);
		ticketDTOList.forEach(ticketDTO -> {
			Ticket ticketUpdate = ticketRepository.findById(ticketDTO.getTicketId()).orElse(null);
			if (ticketUpdate != null) {
				ticketUpdate.setBooking(booking);
				if (booking != null){
					ticketUpdate.setBooked(true);
					ticketUpdate.setUpdatedAt(LocalDateTime.now());
				}
				ticketRepository.save(ticketUpdate);
			}
		});
	}

	@Override
	public Ticket findById(String id) {
		return ticketRepository.findById(id).orElse(null);
	}
}
