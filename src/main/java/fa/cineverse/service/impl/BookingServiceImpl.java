package fa.cineverse.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Booking;
import fa.cineverse.repository.BookingRepository;
import fa.cineverse.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking save(Booking booking) {
		LocalDateTime createdAt = LocalDateTime.now();
		booking.setCreatedAt(createdAt);
		return bookingRepository.save(booking);
	}
	
	

}
