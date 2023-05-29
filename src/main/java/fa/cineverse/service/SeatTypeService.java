package fa.cineverse.service;

import fa.cineverse.model.SeatType;

public interface SeatTypeService {

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:01 AM
	 * @Return: void
	 */
	public void save(SeatType seatType);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: SeatType
	 */
	public SeatType get(String seatId);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:08 AM
	 * @Return: void
	 */
	public void delete(String seatId);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:10 AM
	 * @Return: SeatType
	 */
	public SeatType createSeatType(SeatType seatType);
}
