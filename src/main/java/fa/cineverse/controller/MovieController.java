package fa.cineverse.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.dto.MovieDTO;
import fa.cineverse.model.Movie;
import fa.cineverse.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:53:37 AM
	 * @Return: ResponseEntity<Movie> 
	 */
	@GetMapping("{id}")
	public ResponseEntity<Movie> getMovieById (@PathVariable("id") String id) {
		Movie movie = movieService.get(id);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 8:47:09 AM
	 * @Return: ResponseEntity<?>
	 * @Note: find all
	 */
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<Movie> movieList = movieService.listAll();
		if (movieList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:14:54 PM
	 * @Return: ResponseEntity<?>
	 * @Note: create a movie and check validate input
	 */
	@PostMapping("")
	public ResponseEntity<?> createMovie(@Validated @RequestBody MovieDTO movieDTO, BindingResult bindingResult) {
		Movie movieVal = movieService.findByMovieName(movieDTO.getMovieName());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		if (movieVal != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Movie movie = new Movie();
		BeanUtils.copyProperties(movieDTO, movie);
		movieService.createMovie(movie);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:13:48 PM
	 * @Return: ResponseEntity<Province>
	 * @Note: delete by id check id exited or not if it does then delete it(transfer
	 *        to 1)
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Movie> deleteMovie(@PathVariable("id") String id) {
		Movie movie = this.movieService.get(id);
		if (movie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if ("".equals(id) || "null".equals(id)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			this.movieService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 4:17:55 PM
	 * @Return: ResponseEntity<?>
	 * @Note: update movie moi
	 */
	@PatchMapping(value = "/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable String movieId, @Validated @RequestBody MovieDTO movieDTO,
			BindingResult bindingResult) {
		Movie movieVal = movieService.findByMovieName(movieDTO.getMovieName());

		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		Movie movieOptional = movieService.get(movieId);
		if (movieOptional == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Movie movieUpdate = null;
		if (movieVal != null) {
			if (movieVal.getMovieId() == movieService.get(movieId).getMovieId()) {
				movieOptional.setMovieId(movieId);
				BeanUtils.copyProperties(movieDTO, movieOptional);
				movieUpdate = this.movieService.updateMovie(movieOptional);
				System.out.println(movieOptional.getMovieName());
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			movieOptional.setMovieId(movieId);
			BeanUtils.copyProperties(movieDTO, movieOptional);
			movieUpdate = this.movieService.updateMovie(movieOptional);
		}
		return new ResponseEntity<>(movieUpdate, HttpStatus.OK);
	}
}
