package fa.cineverse.repository;

import fa.cineverse.dto.ScheduleCheckDTO;
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
    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:27 AM
     * @Return: Page<Schedule>
     */
    @Query("SELECT s FROM  Schedule s WHERE s.isDelete = false AND " +
            "(CONCAT(s.movie.movieName,s.room.theater.theaterName,s.room.roomName) LIKE %:keyword% " +
            "OR s.scheduleId.sheduleDateTime LIKE %:keyword%)")
    Page<Schedule> findAll(Pageable pageable, @Param("keyword") String keyword);

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:24 AM
     * @Return: void
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE schedule SET is_delete = 1 WHERE shedule_date_time= :scheduleDateTime AND room_id = :roomId", nativeQuery = true)
    void removeAndUpdate(@Param("scheduleDateTime") LocalDateTime scheduleDateTime, @Param("roomId") String roomId);


    @Query(value = "SELECT shedule_date_time as scheduleDateTime , room_id as roomId " +
            "FROM schedule  " +
            "         INNER JOIN movie m ON schedule.movie_id = m.movie_id " +
            "WHERE room_id = :roomId " +
            "  AND schedule.is_delete = 0 " +
            "  AND ((shedule_date_time >= :startDateTime AND shedule_date_time < :endDateTime) " +
            "       OR ((shedule_date_time + INTERVAL m.duration MINUTE) > :startDateTime AND (shedule_date_time + INTERVAL m.duration MINUTE) <= :endDateTime) " +
            "       OR (shedule_date_time <= :startDateTime AND (shedule_date_time + INTERVAL m.duration MINUTE) > :endDateTime) " +
            ")", nativeQuery = true)
    List<ScheduleCheckDTO> checkSchedule(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("roomId") String roomId);


    List<Schedule> findScheduleByMovie_MovieId(String movieId);


    /**
     * find schedule by movie and schedule and province
     * @param: movieId
     * @param: scheduleDateTime
     * @param: provinceId
     * @return: List<Schedule>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:35:33 AM
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
     * find schedule by movie and schedule
     * @param: movieId
     * @param: scheduleDateTime
     * @return: List<Schedule>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:34:53 AM
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
