package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Movie;

public interface MovieService {	
			
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:50 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMovieIsShowing
	 * @Return: List<Movie>
	 * @Note:
	 */
	List<Movie> findAllMovieIsShowing();

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:53 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMoviePremiereSoon
	 * @Return: List<Movie>
	 * @Note: 
	 */
	List<Movie> findAllMoviePremiereSoon();

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:56 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMoviePremiered
	 * @Return: List<Movie>
	 * @Note:
	 */
	List<Movie> findAllMoviePremiered();
}
