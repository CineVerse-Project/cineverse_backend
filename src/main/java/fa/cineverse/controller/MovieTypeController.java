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

import fa.cineverse.dto.MovieTypeDTO;
import fa.cineverse.model.MovieType;
import fa.cineverse.service.MovieTypeService;

@RestController
@RequestMapping("/movie-type")
public class MovieTypeController {

	@Autowired
	private MovieTypeService movieTypeService;
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 9:37:37 AM
	 * @Return: ResponseEntity<?>
	 * @Note: list all movie type
	 */
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<MovieType> movieTypeList = movieTypeService.listAll();
		if (movieTypeList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movieTypeList, HttpStatus.OK);
	}
	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 9:37:28 AM
	 * @Return: ResponseEntity<?>
	 * @Note: create movie type
	 */
	@PostMapping("")
	public ResponseEntity<?> createMovieType(@Validated @RequestBody MovieTypeDTO movieTypeDTO,
			BindingResult bindingResult) {
		MovieType movieTypeVal = movieTypeService.findByMovieTypeName(movieTypeDTO.getMoveTypeName());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		if (movieTypeVal != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		MovieType movieType = new MovieType();
		BeanUtils.copyProperties(movieTypeDTO, movieType);
		movieTypeService.createMovieType(movieType);
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
	public ResponseEntity<MovieType> deleteMovieType(@PathVariable("id") String id) {
		MovieType movieType = this.movieTypeService.get(id);
		if (movieType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if ("".equals(id) || "null".equals(id)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			this.movieTypeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 19, 2023 | @Time: 4:17:55 PM
	 * @Return: ResponseEntity<?>
	 * @Note: update province moi
	 */
	@PatchMapping(value = "/{movieTypeId}")
	public ResponseEntity<?> updateMovieType(@PathVariable String movieTypeId,
			@Validated @RequestBody MovieTypeDTO movieTypeDTO, BindingResult bindingResult) {
		MovieType movieTypeVal = movieTypeService.findByMovieTypeName(movieTypeDTO.getMoveTypeName());
		
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		MovieType movieTypeOption = movieTypeService.get(movieTypeId);
		if (movieTypeOption==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MovieType movieTypeUpdate = null;
		if (movieTypeVal != null) {
			if (movieTypeVal.getMovieTypeId() == movieTypeId) {
				movieTypeOption.setMovieTypeId(movieTypeId);
				BeanUtils.copyProperties(movieTypeDTO, movieTypeOption);
				movieTypeUpdate = this.movieTypeService.updateMovieType(movieTypeOption);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} else {
			movieTypeOption.setMovieTypeId(movieTypeId);
			BeanUtils.copyProperties(movieTypeDTO, movieTypeOption);
			movieTypeUpdate = this.movieTypeService.updateMovieType(movieTypeOption);
		}

		return new ResponseEntity<>(movieTypeUpdate,HttpStatus.OK);
	}
}
