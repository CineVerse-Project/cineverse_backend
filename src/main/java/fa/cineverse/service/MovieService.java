package fa.cineverse.service;

import fa.cineverse.model.Movie;
import java.util.List;

public interface MovieService {
    Movie findById(String movieId);

//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:50 PM
//	 * @Package: fa.cineverse.service
//	 * @FileName: MovieService.java
//	 * @MethodsName: findAllMovieIsShowing
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMovieIsShowing();
//
//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:53 PM
//	 * @Package: fa.cineverse.service
//	 * @FileName: MovieService.java
//	 * @MethodsName: findAllMoviePremiereSoon
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMoviePremiereSoon();
//
//	/**
//	 * @Author: TriLT6 | @User: TRUNG TRI
//	 * @Day: May 18, 2023 | @Time: 3:51:56 PM
//	 * @Package: fa.cineverse.service
//	 * @FileName: MovieService.java
//	 * @MethodsName: findAllMoviePremiered
//	 * @Return: List<Movie>
//	 * @Note:
//	 */
//	List<Movie> findAllMoviePremiered();
    
    /**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:52:31 AM
	 * @Return: void
	 * @Note: save
	 */
	public void save(Movie movie);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:46:59 AM
	 * @Return: List<Province>
	 * @Note: findAll movie
	 */
	public List<Movie> listAll();

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:47:27 AM
	 * @Return: Province
	 * @Note: findById of movie
	 */
	public Movie get(String movieId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:48:16 AM
	 * @Return: void
	 * @Note: deleteById of movie
	 */
	public void delete(String movieId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:11 PM
	 * @Return: void
	 * @Note: update movie
	 */
	public Movie updateMovie(Movie movie);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:44:27 PM
	 * @Return: Province
	 * @Note: create movie
	 */
	public Movie createMovie(Movie movie);

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:06:03 AM
	 * @Return: Province
	 * @Note: find movie by name
	 */
	public Movie findByMovieName(String movieName);
}
