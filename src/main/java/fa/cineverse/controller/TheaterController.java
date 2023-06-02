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

import fa.cineverse.dto.TheaterDTO;
import fa.cineverse.model.Theater;
import fa.cineverse.service.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	/**
	 * @Author: DatNH20 
	 * @Day: May 29, 2023 | @Time: 8:55:33 AM
	 * @Return: ResponseEntity<Theater>
	 */
	@GetMapping("{id}")
	public ResponseEntity<Theater> getTheaterById (@PathVariable("id") String id) {
		Theater theater = theaterService.get(id);
		return new ResponseEntity<>(theater, HttpStatus.OK);
	}
	
	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 3:37:37 AM
	 * @Return: ResponseEntity<?>
	 * @Note:
	 */
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<Theater> theatertList = theaterService.listAll();
		if (theatertList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(theatertList, HttpStatus.OK);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 3:37:41 AM
	 * @Return: ResponseEntity<?>
	 * @Note:
	 */
	@PostMapping("")
	public ResponseEntity<?> createTheater(@Validated @RequestBody TheaterDTO theaterDTO, BindingResult bindingResult) {
		Theater theaterVal = theaterService.findByTheaterName(theaterDTO.getTheaterName());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
		}
		if (theaterVal != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Theater theater = new Theater();
		BeanUtils.copyProperties(theaterDTO, theater);
		theaterService.createTheater(theater);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 3:37:44 AM
	 * @Return: ResponseEntity<Theater>
	 * @Note:
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Theater> deleteTheater(@PathVariable("id") String id) {
		Theater theater = this.theaterService.get(id);
		if (theater == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if ("".equals(id) || "null".equals("id")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			this.theaterService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 3:37:47 AM
	 * @Return: ResponseEntity<?>
	 * @Note:
	 */
	@PatchMapping(value = "/{theaterId}")
	public ResponseEntity<?> updateTheater(@PathVariable String theaterId,
			@Validated @RequestBody TheaterDTO theaterDTO, BindingResult bindingResult) {
		Theater theaterVal = theaterService.findByTheaterName(theaterDTO.getTheaterName());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
		}
		Theater theaterOptional = theaterService.get(theaterId);
		if (theaterOptional == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Theater theaterUpdate = null;
		if (theaterVal != null) {
			if (theaterVal.getTheaterId() == theaterService.get(theaterId).getTheaterId()) {
				theaterOptional.setTheaterId(theaterId);
				BeanUtils.copyProperties(theaterDTO, theaterOptional);
				theaterUpdate = this.theaterService.updateTheater(theaterOptional);
				System.out.println(theaterOptional.getTheaterName());
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			theaterOptional.setTheaterId(theaterId);
			BeanUtils.copyProperties(theaterDTO, theaterOptional);
			theaterUpdate = this.theaterService.updateTheater(theaterOptional);
			System.out.println("2");
		}
		return new ResponseEntity<>(theaterUpdate, HttpStatus.OK);
	}

}
