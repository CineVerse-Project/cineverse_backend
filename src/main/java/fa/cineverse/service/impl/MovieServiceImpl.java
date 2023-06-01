package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import fa.cineverse.service.MovieService;

/**
 * MovieServiceImpl
 *
 * Version: 1.0
 *
 * Date: May 30, 2023
 *
 * Copyright
 *
 * Modification Log:
 *
 * DATE AUTHOR DESCRIPTION 
 * ----------------------------------------- 
 * May 30,2023 TriLT6
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

  /**
   * 
   * @param:
   * @return:
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:19:51 AM
   */
  @Autowired
  MovieRepository movieRepository;

  /**
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:19:55 AM
   */
  @Override
  public List<Movie> findAllMovieIsShowing() {
    return movieRepository.findAllMovieIsShowing();
  }

  /**
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:19:58 AM
   */
  @Override
  public List<Movie> findAllMoviePremiereSoon() {
    return movieRepository.findAllMoviePremiereSoon();
  }

  /**
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:20:01 AM
   */
  @Override
  public List<Movie> findAllMoviePremiered() {
    return movieRepository.findAllMoviePremiered();
  }

  /**
   * 
   * @param: none
   * @return: List<MovieTop10DTO>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:20:03 AM
   */
  @Override
  public List<MovieTop10DTO> findTop10MovieIsShowing() {
    return movieRepository.findTop10MovieIsShowing();
  }

  /**
   * 
   * @param: none
   * @return: Movie
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:20:05 AM
   */
  @Override
  public Movie findMovieDetailById(String movieId) {
    return movieRepository.findById(movieId).orElse(null);
  }

  /**
   * 
   * @param: movieId
   * @return: Movie
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:20:08 AM
   */
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
		return (List<Movie>) movieRepository.findAllActiveList();
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