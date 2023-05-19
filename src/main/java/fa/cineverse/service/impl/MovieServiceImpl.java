package fa.cineverse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import fa.cineverse.service.MovieService;

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
	 * @Note: Tim kiem top 10 phim DANG cong chieu theo so luong ve ban ra nhieu nhat
	 */
	@Override
	public List<String> findTop10MovieIsShowing() {
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

}
