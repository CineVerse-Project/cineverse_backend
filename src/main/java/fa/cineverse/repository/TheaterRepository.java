package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.Theater;

public interface TheaterRepository extends JpaRepository<Theater, String> {

	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:16:20 AM
	 * @Return: void
	 * @Note: ko xoa nhung chuyen trang thai is-delete tu 0 sang 1
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE theater SET is_delete = 1 WHERE theater_id= :id", nativeQuery = true)
	void deleteTheater(@Param("id") String id);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:16:32 AM
	 * @Return: Theater
	 * @Note: find theater by name
	 */
	Theater findByTheaterName(String theaterName);

	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:45:10 AM
	 * @Return: List<Theater> tìm theater chưa xóa
	 */
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM theater WHERE is_delete = false ", nativeQuery = true)
	List<Theater> findAllActiveList();

}
