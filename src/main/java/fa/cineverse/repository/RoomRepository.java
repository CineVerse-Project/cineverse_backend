package fa.cineverse.repository;

import fa.cineverse.model.Room;
import fa.cineverse.model.Theater;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends JpaRepository<Room, String> {

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:12:36 PM
	 * @Return: void
	 * @Note: ko xoa nhung chuyen trang thai is-delete tu 0 sang 1
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE room SET is_delete = 1 WHERE room_id= :id", nativeQuery = true)
	void deleteRoom(@Param("id") String id);

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:16:47 AM
	 * @Return: Province
	 * @Note: find room by name
	 */
	Room findByRoomName(String RoomName);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 9:52:39 AM
	 * @Return: Room
	 * @Note: find room by theater
	 */
	List<Room> findByTheater(Theater theater);
}
