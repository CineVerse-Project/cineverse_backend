package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Theater;

public interface TheaterService {

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 1:59:35 AM
	 * @Return: void
	 * @Note: save object
	 */
	public void save(Theater theater);

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:00:31 AM
	 * @Return: List<Theater>
	 * @Note: list all of object
	 */
	public List<Theater> listAll();

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:00:44 AM
	 * @Return: Theater
	 * @Note: get object by its id
	 */
	public Theater get(String theaterId);

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:01:23 AM
	 * @Return: void
	 * @Note: delete object by its id
	 */
	public void delete(String theaterId);

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:03:06 AM
	 * @Return: Theater
	 * @Note: update theater
	 */
	public Theater updateTheater(Theater theater);

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:03:18 AM
	 * @Return: Theater
	 * @Note: create theater
	 */
	public Theater createTheater(Theater theater);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:21:21 AM
	 * @Return: Theater
	 * @Note: Find by theater name
	 */
	public Theater findByTheaterName(String theaterName);
}
