package fa.cineverse.repository;

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

}
