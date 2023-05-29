package fa.cineverse.service;


import fa.cineverse.model.ScheduleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.cineverse.model.Schedule;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    Page<Schedule> findAll(Pageable pageable);

    Schedule findById(ScheduleId scheduleId);

    Schedule save(Schedule schedule);

    Schedule update(Schedule schedule);

    void remove(LocalDateTime scheduleDateTime, String roomId);

    List<Schedule> findScheduleByMovieAndScheduleAndProvince(String movieId, LocalDateTime scheduleDateTime, String provinceId);
}
