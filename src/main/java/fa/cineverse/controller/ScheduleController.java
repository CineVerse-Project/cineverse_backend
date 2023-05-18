package fa.cineverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.model.Schedule;
import fa.cineverse.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("")
	public ResponseEntity<Page<Schedule>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable;
		pageable = PageRequest.of(page, size);
		Page<Schedule> schedulePage = scheduleService.findAll(pageable);
		
		return new ResponseEntity<>(schedulePage,HttpStatus.OK);

	}
}
