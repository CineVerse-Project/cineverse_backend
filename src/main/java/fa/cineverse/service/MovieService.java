package fa.cineverse.service;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import java.util.List;

public interface MovieService {
    Movie findById(String movieId);
			
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:50 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMovieIsShowing
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim DANG cong chieu
	 */
	List<Movie> findAllMovieIsShowing();
    
    /**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 7:52:31 AM
	 * @Return: void
	 * @Note: save
	 */
	public void save(Movie movie);

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:53 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMoviePremiereSoon
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim SAP cong chieu
	 */
	List<Movie> findAllMoviePremiereSoon();

	/** @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:46:59 AM
	 * @Return: List<Province>
	 * @Note: findAll movie
	 */
	public List<Movie> listAll();

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 3:51:56 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findAllMoviePremiered
	 * @Return: List<Movie>
	 * @Note: Tim kiem tat ca cac phim DA cong chieu
	 */
	List<Movie> findAllMoviePremiered();
	
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 19, 2023 | @Time: 1:58:00 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findTopMovieIsShowing
	 * @Return: List<Movie>
	 * @Note: Tim kiem top 10 phim DANG cong chieu co so luong ve ban ra nhieu nhat
	 */
	List<MovieTop10DTO> findTop10MovieIsShowing();
	
	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 19, 2023 | @Time: 1:54:39 PM
	 * @Package: fa.cineverse.service
	 * @FileName: MovieService.java
	 * @MethodsName: findMovieDetailById
	 * @Return: Movie
	 * @Note: Tim kiem chi tiet phim theo ma phim
	 */
	Movie findMovieDetailById(String movieId);
	
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
