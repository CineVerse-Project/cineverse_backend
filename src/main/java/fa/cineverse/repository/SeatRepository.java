package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findAllByRoom_RoomId(String roomId);
    
    /**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 3:51:24 PM
	 * @Return: void
	 * @Note: xoa ghe
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE seat SET is_delete = 1 WHERE seat_room_id= :id", nativeQuery = true)
	void deleteSeat(@Param("id") String id);
}
