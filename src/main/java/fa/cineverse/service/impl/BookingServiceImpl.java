package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

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
	public Booking save(Booking booking, List<TicketDTO> ticketDTOList) {
		LocalDateTime createdAt = LocalDateTime.now();
		booking.setCreatedAt(createdAt);
		Booking bookingSaved = bookingRepository.save(booking);
		ticketDTOList.forEach(ticketDTO -> {
			Ticket ticketUpdate = ticketRepository.findById(ticketDTO.getTicketId()).orElse(null);
			if(ticketUpdate!=null){
				ticketUpdate.setBooking(bookingSaved);
				ticketUpdate.setBooked(true);
				ticketUpdate.setUpdatedAt(createdAt);
				ticketRepository.save(ticketUpdate);
			}
		});
		return bookingSaved;
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

}
