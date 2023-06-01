package fa.cineverse.controller;

import fa.cineverse.dto.ScheduleCheckDTO;
import fa.cineverse.dto.ScheduleDTO;
import fa.cineverse.model.Movie;
import fa.cineverse.model.Room;
import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.service.MovieService;
import fa.cineverse.service.RoomService;
import fa.cineverse.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MovieService movieService;

    /**
     * @Author: AnP1
     * @Day: May 18, 2023 | @Time: 3:03:16 PM
     * @Return: ResponseEntity<Page < Schedule>>
     */
    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "") String keyword) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        Page<Schedule> schedulePage = scheduleService.findAll(pageable, keyword);
        if (schedulePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schedulePage, HttpStatus.OK);

    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:03:53 AM
     * @Return: ResponseEntity<?>
     */

    @GetMapping("/detail")
    public ResponseEntity<?> findById(@RequestParam String roomId,
                                      @RequestParam("sheduleDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime sheduleDateTime) {
        ScheduleId scheduleId = new ScheduleId(sheduleDateTime, roomId);
        System.out.println(scheduleId);
        Schedule schedule = scheduleService.findById(scheduleId);
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:03:59 AM
     * @Return: ResponseEntity<?>
     */
    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody ScheduleDTO scheduleDTO, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        scheduleDTO.validate(scheduleDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Schedule schedule = scheduleService.findById(scheduleDTO.getScheduleId());
        if (schedule != null) {
            String errorMessage = "Lịch chiếu này đã tồn tại ở phòng  '" + scheduleDTO.getScheduleId().getRoomId() + "'";
            errors.put("scheduleId", errorMessage);
        }


        Room room = roomService.findById(scheduleDTO.getScheduleId().getRoomId());
        if (room == null) {
            String errorMessage = "Room is not exist";
            errors.put("roomId", errorMessage);
        }

        Movie movie = movieService.findById(scheduleDTO.getMovie().getMovieId());
        if (movie == null || movie.isDelete()) {
            String errorMessage = "Movie is not exist";
            errors.put("movieId", errorMessage);
        } else {
            List<ScheduleCheckDTO> scheduleList = scheduleService.check(scheduleDTO, movie);
            if (!scheduleList.isEmpty()) {
                String errorMessage = "Đã có lịch chiếu khác nằm trong khung giờ này. Vui lòng chọn giờ chiếu khác!!!";
                errors.put("scheduleId", errorMessage);
            }
        }

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Schedule scheduleCreate = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, scheduleCreate);
        scheduleCreate.setRoom(room);
        Schedule scheduleSaved = scheduleService.save(scheduleCreate);
        return new ResponseEntity<>(scheduleSaved, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:03 AM
     * @Return: ResponseEntity<?>
     */
    @PutMapping("")
    public ResponseEntity<?> update(@Validated @RequestBody ScheduleDTO scheduleDTO, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        scheduleDTO.validate(scheduleDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Schedule schedule = scheduleService.findById(scheduleDTO.getScheduleId());
        if (schedule == null) {
            String errorMessage = "Schedule ID is not exist";
            errors.put("scheduleId", errorMessage);
        }

        Room room = roomService.findById(scheduleDTO.getScheduleId().getRoomId());
        if (room == null) {
            String errorMessage = "Room ID is not exist";
            errors.put("roomId", errorMessage);
        }

        Movie movie = movieService.findById(scheduleDTO.getMovie().getMovieId());
        if (movie == null || movie.isDelete()) {
            String errorMessage = "Movie ID is not exist";
            errors.put("movieId", errorMessage);
        }

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Schedule scheduleCreate = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, scheduleCreate);
        scheduleCreate.setRoom(room);
        Schedule scheduleSaved = scheduleService.update(scheduleCreate);
        return new ResponseEntity<>(scheduleSaved, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:08 AM
     * @Return: ResponseEntity<?>
     */
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam("roomId") String roomId,
                                    @RequestParam("sheduleDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime sheduleDateTime) {
        ScheduleId scheduleId = new ScheduleId(sheduleDateTime, roomId);
        Schedule schedule = scheduleService.findById(scheduleId);
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        scheduleService.remove(sheduleDateTime, roomId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Schedule date time does not match formart yyyy-MM-dd'T'HH:mm";
        Map<String, String> errors = new HashMap<>();
        errors.put("sheduleDateTime", errorMessage);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * find schedule by movie and schedule and province
     * 
     * @param: movieId
     * @param: scheduleDateTime
     * @param: provinceId
     * @return: ResponseEntity<List<Schedule>>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:29:09 AM
     */
    @GetMapping("/movie")
    public ResponseEntity<List<Schedule>> findScheduleByMovieAndScheduleAndProvince(@RequestParam String movieId,
                                                 @RequestParam("scheduleDateTime")
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime scheduleDateTime,
                                                 @RequestParam("provinceId") String provinceId) {
        List<Schedule> schedules = scheduleService.findScheduleByMovieAndScheduleAndProvince(movieId, scheduleDateTime, provinceId);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}
