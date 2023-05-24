package fa.cineverse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
    @Query("SELECT s FROM  Schedule s WHERE s.isDelete = false")
    Page<Schedule> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE schedule SET is_delete = 1 WHERE shedule_date_time= :scheduleDateTime AND room_id = :roomId", nativeQuery = true)
    void removeAndUpdate(@Param("scheduleDateTime") LocalDateTime scheduleDateTime, @Param("roomId") String roomId);

}
