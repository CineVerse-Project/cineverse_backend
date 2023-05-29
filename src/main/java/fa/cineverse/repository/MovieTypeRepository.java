package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.MovieType;

public interface MovieTypeRepository extends JpaRepository<MovieType, String> {

	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:13:31 AM
	 * @Return: void
	 * @Note: ko xoa nhung chuyen trang thai is-delete tu 0 sang 1
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE movie_type SET is_delete = 1 WHERE movie_type_id= :id", nativeQuery = true)
	void deleteMovieType(@Param("id") String id);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:13:42 AM
	 * @Return: MovieType
	 * @Note: find movie type by name
	 */
	MovieType findByMoveTypeName(String movieTypeName);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:40:02 AM
	 * @Return: List<MovieType> tìm movie chưa xóa
	 */
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM movie_type WHERE is_delete = false ", nativeQuery = true)
	List<MovieType> findAllActiveList();
}
