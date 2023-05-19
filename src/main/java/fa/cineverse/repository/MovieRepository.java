package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.cineverse.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
			
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
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
	 * @Day: May 19, 2023 | @Time: 9:01:47 AM
	 * @Package: fa.cineverse.repository
	 * @FileName: MovieRepository.java
	 * @MethodsName: findTopMovieIsShowing
	 * @Return: List<String>
	 * @Note: Top 10 phim DANG cong chieu co so luong ve ban ra nhieu nhat 
	 */
	@Query(value = "select count(ticket.ticket_id) as titket_quantity, movie.movie_name "
				 + "from movie "
				 + "inner join schedule on movie.movie_id = schedule.movie_id "
				 + "inner join ticket on schedule.room_id = ticket.room_id  "
				 + "and schedule.shedule_date_time = ticket.schedule_date_time "
				 + "where status = 1 "
				 + "group by schedule.movie_id "
				 + "order by count(ticket.ticket_id) desc "
				 + "limit 10 ", nativeQuery = true)
	List<String> findTop10MovieIsShowing();
}
