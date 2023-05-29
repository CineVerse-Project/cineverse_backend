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

	/**
	 * @Author: HuongNT106
	 * @Day: May 18, 2023 | @Time: 3:08:51 PM
	 * TODO
	 */
	@Override
	public Booking save(Booking booking) {
		LocalDateTime createdAt = LocalDateTime.now();
		booking.setCreatedAt(createdAt);
//		ticketDTOList.forEach(ticketDTO -> {
//			Ticket ticketUpdate = ticketRepository.findById(ticketDTO.getTicketId()).orElse(null);
//			if(ticketUpdate!=null){
//				ticketUpdate.setBooking(bookingSaved);
////				ticketUpdate.setBooked(true);
//				ticketUpdate.setUpdatedAt(createdAt);
//				ticketRepository.save(ticketUpdate);
//			}
//		});
		return bookingRepository.save(booking);
	}

	/**
	 * @Author: HuongNT106
	 * @Day: May 26, 2023 | @Time: 11:33:42 PM
	 * TODO
	 */
	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	/**
	 * @Author: HuongNT106
	 * @Day: May 26, 2023 | @Time: 11:33:47 PM
	 * TODO
	 */
	@Override
	public Optional<Booking> findById(String id) {
		return bookingRepository.findById(id);
	}

	/**
	 * @Author: HuongNT106
	 * @Day: May 26, 2023 | @Time: 11:33:50 PM
	 * TODO
	 */
	@Override
	public Booking changePaymentStatus(String id) {
		LocalDateTime updatedAt = LocalDateTime.now();
		Booking booking = bookingRepository.findById(id).orElse(null);
		List<Ticket> ticketList = ticketRepository.findTicketByBooking_BookingId(id);
		if (booking != null) {
			booking.setPaymentStatus(true);
			booking.setUpdatedAt(updatedAt);
			booking.setTicketTotal(ticketList.size());
			bookingRepository.save(booking);
		}
		return booking;
	}
}
