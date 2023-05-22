package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Room;
import fa.cineverse.model.Theater;

public interface RoomService {

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:45:58 AM
	 * @Return: void
	 * @Note: save Room
	 */
	public void save(Room room);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:46:59 AM
	 * @Return: List<Room>
	 * @Note: findAll Room
	 */
	public List<Room> listAll();

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:47:27 AM
	 * @Return: Room
	 * @Note: findById of Room
	 */
	public Room get(String roomId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:48:16 AM
	 * @Return: void
	 * @Note: deleteById of Room
	 */
	public void delete(String roomId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:11 PM
	 * @Return: void
	 * @Note: update room
	 */
	public Room updateRoom(Room room);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:44:27 PM
	 * @Return: Room
	 * @Note: create room
	 */
	public Room createRoom(Room room);

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:06:03 AM
	 * @Return: Room
	 * @Note: find room by name
	 */
	public Room findByRoomName(String roomName);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 9:53:53 AM
	 * @Return: Room
	 * @Note: find by theater
	 */
	public List<Room> findByTheater(Theater theater);

}
