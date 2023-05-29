package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, String> {
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:21:41 AM
	 * @Return: void
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE seat_type SET is_delete = 1 WHERE seat_type_id= :id", nativeQuery = true)
	void deleteSeatType(@Param ("id") String id);

	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:44:31 AM
	 * @Return: List<SeatType> tìm seat type chưa xóa
	 */
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM seat_type WHERE is_delete = false ", nativeQuery = true)
	List<SeatType> findAllActiveList();
}
