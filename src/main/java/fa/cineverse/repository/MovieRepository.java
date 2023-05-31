package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

	 /** @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:32:52 PM
	 * @Package: fa.cineverse.repository
	 * @FileName: MovieRepository.java
	 * @MethodsName: findAllMovieIsShowing
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim DANG cong chieu
	 */
	@Query(value = "select * "
			     + "from cineverse.movie "
			     + "where status = 1 ", nativeQuery = true)
	List<Movie> findAllMovieIsShowing();	

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:33:20 PM
	 * @Package: fa.cineverse.repository
	 * @FileName: MovieRepository.java
	 * @MethodsName: findAllMoviePremiereSoon
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim SAP cong chieu
	 */
	@Query(value = "select * "
			     + "from cineverse.movie "
			     + "where status = 2 ", nativeQuery = true)
	List<Movie> findAllMoviePremiereSoon();	

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:33:22 PM
	 * @Package: fa.cineverse.repository
	 * @FileName: MovieRepository.java
	 * @MethodsName: findAllMoviePremiered
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim DA cong chieu
	 */
	@Query(value = "select * "
				 + "from cineverse.movie "
				 + "where status = 0 ", nativeQuery = true)
	List<Movie> findAllMoviePremiered();	
	
	
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 26, 2023 | @Time: 6:38:28 PM
	 * @Package: fa.cineverse.repository
	 * @FileName: MovieRepository.java
	 * @MethodsName: findTop10MovieIsShowing
	 * @Return: List<MovieTop10DTO>
	 * @Note: Tim kiem top 10 phim dang cong chieu
	 */
	@Query(value = "select count(ticket.ticket_id) as quantity, "
			     + "movie.movie_id as movieId, "
				 + "movie.movie_name as movieName, "
				 + "movie.image_url as imageUrl "
				 + "from movie "
				 + "inner join schedule on movie.movie_id = schedule.movie_id "
				 + "inner join ticket on schedule.room_id = ticket.room_id  "
				 + "and schedule.shedule_date_time = ticket.schedule_date_time "
				 + "where status = 1 "
				 + "group by schedule.movie_id "
				 + "order by count(ticket.ticket_id) desc "
				 + "limit 10 ", nativeQuery = true)
	List<MovieTop10DTO> findTop10MovieIsShowing();

	 /** @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:49:56 AM
	 * @Return: void
	 * @Note: deletes
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE movie SET is_delete = 1 WHERE movie_id= :id", nativeQuery = true)
	void deleteMovie(@Param("id") String id);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:50:02 AM
	 * @Return: Movie
	 * @Note: find by name
	 */
	Movie findByMovieName(String movieName);
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:41:27 AM
	 * @Return: List<Movie> tìm movie chưa xóa
	 */
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM movie WHERE is_delete = false ", nativeQuery = true)
	List<Movie> findAllActiveList();
}
