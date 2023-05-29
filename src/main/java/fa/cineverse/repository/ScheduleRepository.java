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
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
    @Query("SELECT s FROM  Schedule s WHERE s.isDelete = false")
    Page<Schedule> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE schedule SET is_delete = 1 WHERE shedule_date_time= :scheduleDateTime AND room_id = :roomId", nativeQuery = true)
    void removeAndUpdate(@Param("scheduleDateTime") LocalDateTime scheduleDateTime, @Param("roomId") String roomId);


    List<Schedule> findScheduleByMovie_MovieId(String movieId);

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:36:17 PM
     * @Return: List<Schedule>
     */
    @Query(value = "select *\n" +
            "from schedule s\n" +
            "         join room r on s.room_id = r.room_id\n" +
            "         join theater t on r.theater_id = t.theater_id\n" +
            "         join province p on t.province_id = p.province_id\n" +
            "where s.movie_id = :movieId\n" +
            "  and date(s.shedule_date_time) = :scheduleDateTime and p.province_id = :provinceId " +
            "and s.shedule_date_time > CURRENT_TIMESTAMP\n" +
            "order by s.shedule_date_time" , nativeQuery = true)
    List<Schedule> findScheduleByMovieAndScheduleAndProvince(@Param("movieId") String movieId,
                                                             @Param("scheduleDateTime") LocalDateTime scheduleDateTime,
                                                             @Param("provinceId") String provinceId);

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:36:21 PM
     * @Return: List<Schedule>
     */
    @Query(value = "select *\n" +
            "from schedule s\n" +
            "         join room r on s.room_id = r.room_id\n" +
            "         join theater t on r.theater_id = t.theater_id\n" +
            "         join province p on t.province_id = p.province_id\n" +
            "where s.movie_id = :movieId\n" +
            "  and date(s.shedule_date_time) = :scheduleDateTime\n" +
            "and s.shedule_date_time >= CURRENT_TIMESTAMP\n" +
            "order by s.shedule_date_time", nativeQuery = true)
    List<Schedule> findScheduleByMovieAndSchedule(@Param("movieId") String movieId,
                                                  @Param("scheduleDateTime") LocalDateTime scheduleDateTime);
}
