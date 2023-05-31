package fa.cineverse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.dto.MovieTop10DTO;
import fa.cineverse.model.Movie;
import fa.cineverse.service.MovieService;

@RestController
public class HomeController {

	@Autowired
	MovieService movieService;

	@GetMapping("/movie-is-showing")
	public ResponseEntity<?> HomeMovieIsShowing() {
		List<Movie> movieListIsShowing = movieService.findAllMovieIsShowing();
		if (movieListIsShowing.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieListIsShowing, HttpStatus.OK);
	}

	@GetMapping("/movie-premiere-soon")
	public ResponseEntity<?> HomeMoviePremiereSoon() {
		List<Movie> movieListPremiereSoon = movieService.findAllMoviePremiereSoon();
		if (movieListPremiereSoon.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieListPremiereSoon, HttpStatus.OK);
	}

	@GetMapping("/movie-premiered")
	public ResponseEntity<?> HomeMoviePremiered() {
		List<Movie> movieListPremiered = movieService.findAllMoviePremiered();
		if (movieListPremiered.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieListPremiered, HttpStatus.OK);
	}

	@GetMapping("/movie-top-10-is-showing")
	public ResponseEntity<?> HomeMovieTop10IsShowing() {
		List<MovieTop10DTO> movieListTop10IsShowing = movieService.findTop10MovieIsShowing();
		if (movieListTop10IsShowing.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieListTop10IsShowing, HttpStatus.OK);
	}

	@GetMapping("/movie-detail/{movieId}")
	public ResponseEntity<?> HomeMovieDetail(@PathVariable String movieId) {
		Movie movieDetail = movieService.findMovieDetailById(movieId);
		if (movieDetail == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieDetail, HttpStatus.OK);
	}
}
