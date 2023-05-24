package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
