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
	 * @Note: Tim kiem tat ca cac phim DANG cong chieu
	 */
	List<Movie> findAllMovieIsShowing();

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
	List<String> findTop10MovieIsShowing();
	
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
}
