package fa.cineverse.service;

import fa.cineverse.model.Seat;

public interface SeatService {
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 9:00:32 AM
	 * @Return: void
	 * @Note: save
	 */
	public void save(Seat seat);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 9:00:38 AM
	 * @Return: Seat
	 * @Note: get
	 */
	public Seat get (String seatId);
	
	//public List<Seat> listAll();
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 9:00:49 AM
	 * @Return: void
	 * @Note: delete
	 */
	public void delete(String seatID);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 9:00:58 AM
	 * @Return: Seat
	 * @Note: create
	 */
	public Seat createSeat(Seat seat);

}
