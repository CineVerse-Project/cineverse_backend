package fa.cineverse.service.impl;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import fa.cineverse.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:39:50 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @Note:
	 */
	@Autowired
	MovieRepository movieRepository;

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:39:56 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @MethodsName: findAllMovieIsShowing
	 * @Note: Tim kiem tat ca cac phim DANG cong chieu
	 */
	@Override
	public List<Movie> findAllMovieIsShowing() {
		return movieRepository.findAllMovieIsShowing();
	}

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:40:01 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @MethodsName: findAllMoviePremiereSoon
	 * @Note: Tim kiem tat ca cac phim SAP cong chieu
	 */
	@Override
	public List<Movie> findAllMoviePremiereSoon() {
		return movieRepository.findAllMoviePremiereSoon();
	}

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 18, 2023 | @Time: 4:40:05 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @MethodsName: findAllMoviePremiered
	 * @Note: Tim kiem tat ca cac phim DA cong chieu
	 */
	@Override
	public List<Movie> findAllMoviePremiered() {
		return movieRepository.findAllMoviePremiered();
	}

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 19, 2023 | @Time: 2:00:47 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @MethodsName: findTopMovieIsShowing
	 * @Note: Tim kiem top 10 phim DANG cong chieu theo so luong ve ban ra nhieu
	 *        nhat
	 */
	@Override
	public List<MovieTop10DTO> findTop10MovieIsShowing() {
		return movieRepository.findTop10MovieIsShowing();
	}

	/**
	 * @Author: TriLT6 | @User: TRUNG TRI
	 * @Day: May 19, 2023 | @Time: 2:00:56 PM
	 * @Package: fa.cineverse.service.impl
	 * @FileName: MovieServiceImpl.java
	 * @MethodsName: findMovieDetailById
	 * @Note: Tim kiem chi tiet phim theo ma phim
	 */
	@Override
	public Movie findMovieDetailById(String movieId) {
		return movieRepository.findById(movieId).orElse(null);
	}

	@Override
	public Movie findById(String movieId) {
		return movieRepository.findById(movieId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: save
	 */
	@Override
	public void save(Movie movie) {
		this.movieRepository.save(movie);

	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: list All
	 */
	@Override
	public List<Movie> listAll() {
		// TODO Auto-generated method stub
		return (List<Movie>) movieRepository.findAll();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: get
	 */
	@Override
	public Movie get(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: delete
	 */
	@Override
	public void delete(String movieId) {
		// TODO Auto-generated method stub
		movieRepository.deleteMovie(movieId);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: update
	 */
	@Override
	public Movie updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		LocalDateTime nowDateTime = LocalDateTime.now();
		movie.setUpdatedAt(nowDateTime);
		return this.movieRepository.save(movie);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: create
	 */
	@Override
	public Movie createMovie(Movie movie) {
		LocalDateTime nowDateTime = LocalDateTime.now();
		movie.setCreatedAt(nowDateTime);
		movie.setUpdatedAt(null);
		return movieRepository.save(movie);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: find by movie name
	 */
	@Override
	public Movie findByMovieName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.findByMovieName(movieName);
	}
}
