package fa.cineverse.service;

import fa.cineverse.dto.ScheduleCheckDTO;
import fa.cineverse.dto.ScheduleDTO;
import fa.cineverse.model.Movie;
import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface ScheduleService {
    Page<Schedule> findAll(Pageable pageable, String keyword);

    Schedule findById(ScheduleId scheduleId);

    Schedule save(Schedule schedule);

    Schedule update(Schedule schedule);

    void remove(LocalDateTime scheduleDateTime, String roomId);


    List<ScheduleCheckDTO> check(ScheduleDTO scheduleDTO, Movie movie);


    List<Schedule> findScheduleByMovieAndScheduleAndProvince(String movieId, LocalDateTime scheduleDateTime, String provinceId);

}
