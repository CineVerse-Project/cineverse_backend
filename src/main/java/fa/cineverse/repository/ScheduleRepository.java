package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {

}
