package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
			
//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:30 PM
//	 * @Package: fa.cineverse.repository
//	 * @FileName: MovieRepository.java
//	 * @MethodsName: findAllMovieIsShowing
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMovieIsShowing();
//
//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:32 PM
//	 * @Package: fa.cineverse.repository
//	 * @FileName: MovieRepository.java
//	 * @MethodsName: findAllMoviePremiereSoon
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMoviePremiereSoon();
//
//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:36 PM
//	 * @Package: fa.cineverse.repository
//	 * @FileName: MovieRepository.java
//	 * @MethodsName: findAllMoviePremiered
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMoviePremiered();
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:49:56 AM
	 * @Return: void
	 * @Note: delete
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
}
