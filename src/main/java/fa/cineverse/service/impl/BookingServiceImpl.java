package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Ticket;
import fa.cineverse.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Booking;
import fa.cineverse.repository.BookingRepository;
import fa.cineverse.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Booking save(Booking booking) {
		booking.setCreatedAt(LocalDateTime.now());
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Optional<Booking> findById(String id) {
		return bookingRepository.findById(id);
	}

	@Override
	public Booking changePaymentStatus(String id) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		List<Ticket> ticketList = ticketRepository.findTicketByBooking_BookingId(id);
		if (booking != null) {
			booking.setPaymentStatus(true);
			booking.setUpdatedAt(LocalDateTime.now());
			booking.setTicketTotal(ticketList.size());
			bookingRepository.save(booking);
		}
		return booking;
	}
}
