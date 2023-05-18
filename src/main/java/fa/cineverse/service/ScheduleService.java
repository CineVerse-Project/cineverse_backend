package fa.cineverse.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.cineverse.model.Schedule;

public interface ScheduleService {
    Page<Schedule> findAll(Pageable pageable);
}
