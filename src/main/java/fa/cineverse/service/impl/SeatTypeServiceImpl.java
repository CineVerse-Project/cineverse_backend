package fa.cineverse.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.SeatType;
import fa.cineverse.repository.SeatTypeRepository;
import fa.cineverse.service.SeatTypeService;

@Service
public class SeatTypeServiceImpl implements SeatTypeService {
	
	@Autowired
	private SeatTypeRepository seatTypeRepository;

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: save
	 */
	@Override
	public void save(SeatType seatType) {
		this.seatTypeRepository.save(seatType);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: get
	 */
	@Override
	public SeatType get(String seatId) {
		return seatTypeRepository.findById(seatId).orElse(null);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: delete
	 */
	@Override
	public void delete(String seatId) {
		seatTypeRepository.deleteSeatType(seatId);		
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: create
	 */
	@Override
	public SeatType createSeatType(SeatType seatType) {
		LocalDateTime now = LocalDateTime.now();
		seatType.setCreatedAt(now);
		seatType.setUpdatedAt(null);
 		return seatTypeRepository.save(seatType);
	}

}
