package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Room;
import fa.cineverse.model.Theater;
import fa.cineverse.repository.RoomRepository;

import fa.cineverse.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:59:45 AM
	 * @Note: Service save a room
	 */
	@Override
	public void save(Room room) {
		this.roomRepository.save(room);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:11 AM
	 * @Note: Service list all room
	 */
	@Override
	public List<Room> listAll() {
		return (List<Room>) roomRepository.findAll();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:34 AM
	 * @Note: Service get a room by id
	 */
	@Override
	public Room get(String roomId) {
		return roomRepository.findById(roomId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:47 AM
	 * @Note: Service delete a room by id
	 */
	@Override
	public void delete(String roomId) {
		roomRepository.deleteRoom(roomId);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:53 PM
	 * @Note: update room
	 */
	@Override
	public Room updateRoom(Room room) {
		LocalDateTime now = LocalDateTime.now();
		room.setUpdatedAt(now);
		return this.roomRepository.save(room);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:27 AM
	 * @Note: create room
	 */
	@Override
	public Room createRoom(Room room) {
		LocalDateTime now = LocalDateTime.now();
		room.setCreatedAt(now);
		room.setUpdatedAt(null);
		return roomRepository.save(room);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:35 AM
	 * @Note: find room by name
	 */
	@Override
	public Room findByRoomName(String roomName) {
		return roomRepository.findByRoomName(roomName);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 9:54:31 AM
	 * @Note: find by theater
	 */
	@Override
	public List<Room> findByTheater(Theater theater) {
		// TODO Auto-generated method stub
		return (List<Room>) roomRepository.findByTheater(theater);
	}

}
