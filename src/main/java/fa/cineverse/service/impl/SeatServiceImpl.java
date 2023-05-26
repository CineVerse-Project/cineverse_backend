package fa.cineverse.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Seat;
import fa.cineverse.repository.SeatRepository;
import fa.cineverse.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 9:06:49 AM
	 * @Note: 
	 */
	@Override
	public void save(Seat seat) {
		this.seatRepository.save(seat);
		
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 9:06:53 AM
	 * @Note: 
	 */
	@Override
	public void delete(String seatID) {
		// TODO Auto-generated method stub
		seatRepository.deleteSeat(seatID);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 9:06:56 AM
	 * @Note: 
	 */
	@Override
	public Seat createSeat(Seat seat) {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();
		seat.setCreatedAt(now);
		seat.setUpdatedAt(null);
		return seatRepository.save(seat);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 9:07:03 AM
	 * @Note: 
	 */
	@Override
	public Seat get(String seatId) {
		// TODO Auto-generated method stub
		return seatRepository.findById(seatId).orElse(null);
	}

}
