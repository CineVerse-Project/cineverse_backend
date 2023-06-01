package fa.cineverse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import fa.cineverse.service.MovieService;

/**
 * HomeController
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
@RestController
public class HomeController {

  /**
   * Put the movieRepository in and use its functions
   * 
   * @param: none
   * @return: none
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:57:59 AM
   */
  @Autowired
  MovieService movieService;

  /**
   * Show a list of all movies currently playing
   * 
   * @param: none
   * @return: ResponseEntity<?>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:43:11 AM
   */
  @GetMapping("/movie-is-showing")
  public ResponseEntity<?> HomeMovieIsShowing() {
    List<Movie> movieListIsShowing = movieService.findAllMovieIsShowing();
    if (movieListIsShowing.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(movieListIsShowing, HttpStatus.OK);
  }

  /**
   * Show a list of all upcoming movies
   * 
   * @param: none
   * @return: ResponseEntity<?>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:43:58 AM
   */
  @GetMapping("/movie-premiere-soon")
  public ResponseEntity<?> HomeMoviePremiereSoon() {
    List<Movie> movieListPremiereSoon = movieService.findAllMoviePremiereSoon();
    if (movieListPremiereSoon.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(movieListPremiereSoon, HttpStatus.OK);
  }

  /**
   * Show list of all released movies
   * 
   * @param: none
   * @return: ResponseEntity<?>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:44:01 AM
   */
  @GetMapping("/movie-premiered")
  public ResponseEntity<?> HomeMoviePremiered() {
    List<Movie> movieListPremiered = movieService.findAllMoviePremiered();
    if (movieListPremiered.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(movieListPremiered, HttpStatus.OK);
  }

  /**
   * Show a list of the top 10 movies currently showing according to the number
   * of tickets booked
   * 
   * @param: none
   * @return: ResponseEntity<?>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:44:05 AM
   */
  @GetMapping("/movie-top-10-is-showing")
  public ResponseEntity<?> HomeMovieTop10IsShowing() {
    List<MovieTop10DTO> movieListTop10IsShowing = movieService
        .findTop10MovieIsShowing();
    if (movieListTop10IsShowing.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(movieListTop10IsShowing, HttpStatus.OK);
  }

  /**
   * Show detailed information about the movie
   * 
   * @param: movieId
   * @return: ResponseEntity<?>
   * @throws: none
   * @Author: TriLT6 | @User: TRUNG TRI
   * @Day: May 30, 2023 | @Time: 9:44:16 AM
   */
  @GetMapping("/movie-detail/{movieId}")
  public ResponseEntity<?> HomeMovieDetail(@PathVariable String movieId) {
    Movie movieDetail = movieService.findMovieDetailById(movieId);
    if (movieDetail == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(movieDetail, HttpStatus.OK);
  }
}
