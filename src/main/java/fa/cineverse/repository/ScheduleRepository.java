package fa.cineverse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
	Page<Schedule> findAll(Pageable pageable);
}
