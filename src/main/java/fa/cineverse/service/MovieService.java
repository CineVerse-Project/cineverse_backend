package fa.cineverse.service;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import java.util.List;

/**
 * MovieService
 *
 * Version: 1.0
 *
 * Date: May 30, 2023
 *
 * Copyright
 *
 * Modification Log:
 *
 * DATE AUTHOR DESCRIPTION ----------------------------------------- May 30,
 * 2023 TriLT6
 *
 */
public interface MovieService {

  /**
   * Show detailed information about the movie
   * 
   * @param: movieId
   * @return: Movie
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:08:02 AM
   */
  Movie findById(String movieId);

  /**
   * Show a list of all movies currently playing
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:07:06 AM
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
   * Show a list of all upcoming movies
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:07:09 AM
   */
  List<Movie> findAllMoviePremiereSoon();

  /**
   * @Author: DatNH20
   * @Day: May 19, 2023 | @Time: 7:46:59 AM
   * @Return: List<Province>
   * @Note: findAll movie
   */
  public List<Movie> listAll();

  /**
   * Show list of all released movies
   * 
   * @param: none
   * @return: List<Movie>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:07:16 AM
   */
  List<Movie> findAllMoviePremiered();

  /**
   * Show a list of the top 10 movies currently showing according to the number
   * of tickets booked
   * 
   * @param: none
   * @return: List<MovieTop10DTO>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:07:23 AM
   */
  List<MovieTop10DTO> findTop10MovieIsShowing();

  /**
   * Show detailed information about the movie
   * 
   * @param: movieId
   * @return: Movie
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 10:07:30 AM
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
