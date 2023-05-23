package fa.cineverse.service;



import fa.cineverse.model.ScheduleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.cineverse.model.Schedule;

import java.time.LocalDateTime;

public interface ScheduleService {
    Page<Schedule> findAll(Pageable pageable);

    Schedule findById(ScheduleId scheduleId);

    Schedule save(Schedule schedule);

    Schedule update(Schedule schedule);

    void remove(LocalDateTime scheduleDateTime, String roomId);
}
