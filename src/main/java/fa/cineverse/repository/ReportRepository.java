package fa.cineverse.repository;

import fa.cineverse.dto.*;
import fa.cineverse.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface ReportRepository extends JpaRepository<Booking, String> {
    /**
     *
     */
    @Query(value = "select sum(t.price) as revenue, date(b.created_at) as time " +
            "from booking b " +
            "         join ticket t on b.booking_id = t.booking_id " +
            "where b.payment_status = true " +
            "  and month(b.created_at) = month(:month) and year(b.created_at) = year(:month);", nativeQuery = true)
    List<RevenueDTO> revenueByMonth(@Param("month") LocalDate month);

    /**
     *
     */
    @Query(value = "SELECT calendar.date AS time, COALESCE(SUM(CASE WHEN (b.payment_status = true OR b.payment_status IS NULL) THEN COALESCE(t.price, 0) ELSE 0 END), 0) AS revenue " +
            "FROM ( " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "         FROM ( " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "              ) AS a " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS b " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS c " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "     ) AS calendar " +
            "         LEFT JOIN booking b ON DATE(b.created_at) = calendar.date " +
            "         LEFT JOIN ticket t ON b.booking_id = t.booking_id " +
            "GROUP BY calendar.date " +
            "ORDER BY calendar.date", nativeQuery = true)
    List<RevenueDTO> revenueByPeriodTime(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    /**
     *
     */
    @Query(value = "SELECT calendar.date AS time, COALESCE(SUM(CASE WHEN m.movie_id = :movieId AND (b.payment_status = true OR b.payment_status IS NULL) THEN COALESCE(t.price, 0) ELSE 0 END), 0) AS revenue " +
            "FROM ( " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "         FROM ( " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "              ) AS a " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS b " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS c " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "     ) AS calendar " +
            "         LEFT JOIN booking b ON DATE(b.created_at) = calendar.date " +
            "         LEFT JOIN ticket t ON b.booking_id = t.booking_id " +
            "         LEFT JOIN schedule s ON t.room_id = s.room_id AND t.schedule_date_time = s.shedule_date_time " +
            "         LEFT JOIN movie m ON s.movie_id = m.movie_id " +
            "WHERE calendar.date BETWEEN :startDate AND :endDate " +
            "GROUP BY calendar.date " +
            "ORDER BY calendar.date;", nativeQuery = true)
    List<RevenueDTO> revenueByPeriodTimeAndMovie(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("movieId") String movieId);

    /**
     *
     */
    @Query(value = "SELECT calendar.date AS time, COALESCE(SUM(CASE WHEN tt.theater_id = :theaterId AND (b.payment_status = true OR b.payment_status IS NULL) THEN COALESCE(t.price, 0) ELSE 0 END), 0) AS revenue " +
            "FROM ( " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "         FROM ( " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "              ) AS a " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS b " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS c " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "     ) AS calendar " +
            "         LEFT JOIN booking b ON DATE(b.created_at) = calendar.date " +
            "         LEFT JOIN ticket t ON b.booking_id = t.booking_id " +
            "         LEFT JOIN schedule s ON t.room_id = s.room_id AND t.schedule_date_time = s.shedule_date_time " +
            "         LEFT JOIN room r on s.room_id = r.room_id " +
            "        LEFT JOIN theater tt on r.theater_id = tt.theater_id " +
            "WHERE calendar.date BETWEEN :startDate AND :endDate " +
            "GROUP BY calendar.date " +
            "ORDER BY calendar.date;", nativeQuery = true)
    List<RevenueDTO> revenueByPeriodTimeAndTheater(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("theaterId") String theater);

    /**
     *
     */
    @Query(value = "SELECT calendar.date AS time, COALESCE(SUM(CASE WHEN p.province_id= :provinceId AND (b.payment_status = true OR b.payment_status IS NULL) THEN COALESCE(t.price, 0) ELSE 0 END), 0) AS revenue " +
            "FROM ( " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "         FROM ( " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "              ) AS a " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS b " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS c " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "     ) AS calendar " +
            "         LEFT JOIN booking b ON DATE(b.created_at) = calendar.date " +
            "         LEFT JOIN ticket t ON b.booking_id = t.booking_id " +
            "         LEFT JOIN schedule s ON t.room_id = s.room_id AND t.schedule_date_time = s.shedule_date_time " +
            "         LEFT JOIN room r on s.room_id = r.room_id " +
            "         LEFT JOIN theater tt on r.theater_id = tt.theater_id " +
            "        LEFT JOIN province p on tt.province_id = p.province_id " +
            "WHERE calendar.date BETWEEN :startDate AND :endDate " +
            "GROUP BY calendar.date " +
            "ORDER BY calendar.date; ", nativeQuery = true)
    List<RevenueDTO> revenueByPeriodTimeAndProvince(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("provinceId") String province);

    @Query(value = "SELECT months.month AS time, COALESCE(revenue.revenue, 0) AS revenue " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, SUM(t.price) AS revenue " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year  " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS revenue ON months.month = revenue.booking_month", nativeQuery = true)
    List<RevenueDTO> revenueMonthInYear(@Param("year") String year);

    @Query(value = "SELECT months.month AS time, COALESCE(revenue.revenue, 0) AS revenue " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COALESCE(SUM(t.price), 0) AS revenue " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "             JOIN movie m ON s.movie_id = m.movie_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND m.movie_id = :movieId " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS revenue ON months.month = revenue.booking_month;", nativeQuery = true)
    List<RevenueDTO> revenueMonthInYearAndMovie(@Param("year") String year, @Param("movieId") String movieId);

    @Query(value = "SELECT months.month AS time, COALESCE(revenue.revenue, 0) AS revenue " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COALESCE(SUM(t.price), 0) AS revenue " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "            JOIN room r on s.room_id = r.room_id " +
            "             JOIN theater t2 on r.theater_id = t2.theater_id " +
            " " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND t2.theater_id = :theaterId " +
            ") AS revenue ON months.month = revenue.booking_month;", nativeQuery = true)
    List<RevenueDTO> revenueMonthInYearAndTheater(@Param("year") String year, @Param("theaterId") String theaterId);

    /**
     * @param year
     * @param provinceId
     * @return
     */
    @Query(value = "SELECT months.month AS time, COALESCE(revenue.revenue, 0) AS revenue " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COALESCE(SUM(t.price), 0) AS revenue " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "             JOIN room r on s.room_id = r.room_id " +
            "             JOIN theater t2 on r.theater_id = t2.theater_id " +
            "             JOIN  province p on t2.province_id = p.province_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND p.province_id = :provinceId " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS revenue ON months.month = revenue.booking_month;", nativeQuery = true)
    List<RevenueDTO> revenueMonthInYearAndProvince(@Param("year") String year, @Param("provinceId") String provinceId);

    @Query(value="SELECT months.month AS time, COALESCE(ticket_count.count, 0) AS ticketCount " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COUNT(t.ticket_id) AS count " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS ticket_count ON months.month = ticket_count.booking_month;" , nativeQuery=true)
    List<TicketSoldDTO> ticketMonthInYear(@Param("year") String year);

    @Query(value="SELECT months.month AS time, COALESCE(ticket_count.count, 0) AS ticketCount " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COUNT(t.ticket_id) AS count " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND s.movie_id = :movieId " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS ticket_count ON months.month = ticket_count.booking_month;", nativeQuery=true)
    List<TicketSoldDTO> ticketMonthInYearAndMovie(@Param("year") String year, @Param("movieId") String movieId);

    @Query(value="SELECT months.month AS time, COALESCE(ticket_count.count, 0) AS ticketCount " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COUNT(t.ticket_id) AS count " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "             JOIN room r on s.room_id = r.room_id " +
            "             JOIN theater th on r.theater_id = th.theater_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND th.theater_id = :theaterId " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS ticket_count ON months.month = ticket_count.booking_month;",nativeQuery=true)
    List<TicketSoldDTO> ticketMonthInYearAndTheater(@Param("year") String year, @Param("theaterId") String theaterId);

    @Query(value="SELECT months.month AS time, COALESCE(ticket_count.count, 0) AS ticketCount " +
            "FROM ( " +
            "         SELECT 1 AS month " +
            "         UNION SELECT 2 " +
            "         UNION SELECT 3 " +
            "         UNION SELECT 4 " +
            "         UNION SELECT 5 " +
            "         UNION SELECT 6 " +
            "         UNION SELECT 7 " +
            "         UNION SELECT 8 " +
            "         UNION SELECT 9 " +
            "         UNION SELECT 10 " +
            "         UNION SELECT 11 " +
            "         UNION SELECT 12 " +
            "     ) AS months " +
            "         LEFT JOIN ( " +
            "    SELECT DATE_FORMAT(b.created_at, '%m') AS booking_month, COUNT(t.ticket_id) AS count " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "             JOIN room r on s.room_id = r.room_id " +
            "             JOIN theater t2 on r.theater_id = t2.theater_id " +
            "             JOIN  province p on t2.province_id = p.province_id " +
            "    WHERE b.payment_status = true " +
            "      AND YEAR(b.created_at) = :year " +
            "      AND p.province_id = :provinceId " +
            "    GROUP BY DATE_FORMAT(b.created_at, '%m') " +
            ") AS ticket_count ON months.month = ticket_count.booking_month;", nativeQuery= true)
    List<TicketSoldDTO> ticketMonthInYearAndProvince(@Param("year") String year, @Param("provinceId") String provinceId);

    @Query(value="SELECT calendar.date AS time, COALESCE(ticket_count.count, 0) AS ticketCount " +
            "FROM ( " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "         FROM ( " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "              ) AS a " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS b " +
            "                  CROSS JOIN ( " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "         ) AS c " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "     ) AS calendar " +
            "         LEFT JOIN ( " +
            "    SELECT DATE(b.created_at) AS booking_date, COUNT(t.ticket_id) AS count " +
            "    FROM booking b " +
            "             JOIN ticket t ON b.booking_id = t.booking_id " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id " +
            "    WHERE b.payment_status = true " +
            "    GROUP BY DATE(b.created_at) " +
            ") AS ticket_count ON calendar.date = ticket_count.booking_date " +
            "ORDER BY calendar.date;", nativeQuery=true)
    List<TicketSoldDTO> ticketByPeriodTime(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value="SELECT calendar.date AS time, COALESCE(ticket_count.count, 0) AS ticketCount  " +
            "FROM (  " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date  " +
            "         FROM (  " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "              ) AS a  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS b  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS c  " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate  " +
            "     ) AS calendar  " +
            "         LEFT JOIN (  " +
            "    SELECT DATE(b.created_at) AS booking_date, COUNT(t.ticket_id) AS count  " +
            "    FROM booking b  " +
            "             JOIN ticket t ON b.booking_id = t.booking_id  " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id  " +
            "    WHERE b.payment_status = true  " +
            "      AND s.movie_id = :movieId  " +
            "    GROUP BY DATE(b.created_at)  " +
            ") AS ticket_count ON calendar.date = ticket_count.booking_date  " +
            "ORDER BY calendar.date;",nativeQuery=true)
    List<TicketSoldDTO> ticketByPeriodTimeAndMovie(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("movieId") String movieId);

    @Query(value="SELECT calendar.date AS time, COALESCE(ticket_count.count, 0) AS ticketCount  " +
            "FROM (  " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date  " +
            "         FROM (  " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "              ) AS a  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS b  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS c  " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate  " +
            "     ) AS calendar  " +
            "         LEFT JOIN (  " +
            "    SELECT DATE(b.created_at) AS booking_date, COUNT(t.ticket_id) AS count  " +
            "    FROM booking b  " +
            "             JOIN ticket t ON b.booking_id = t.booking_id  " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id  " +
            "             JOIN room r on s.room_id = r.room_id  " +
            "             JOIN theater th on r.theater_id = th.theater_id  " +
            "    WHERE b.payment_status = true  " +
            "      AND th.theater_id = :theaterId  " +
            "    GROUP BY DATE(b.created_at)  " +
            ") AS ticket_count ON calendar.date = ticket_count.booking_date  " +
            "ORDER BY calendar.date;", nativeQuery=true)
    List<TicketSoldDTO> ticketByPeriodTimeAndTheater(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("theaterId") String theater);

    @Query(value="SELECT calendar.date AS time, COALESCE(ticket_count.count, 0) AS ticketCount  " +
            "FROM (  " +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date  " +
            "         FROM (  " +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "              ) AS a  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS b  " +
            "                  CROSS JOIN (  " +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9  " +
            "         ) AS c  " +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate  " +
            "     ) AS calendar  " +
            "         LEFT JOIN (  " +
            "    SELECT DATE(b.created_at) AS booking_date, COUNT(t.ticket_id) AS count  " +
            "    FROM booking b  " +
            "             JOIN ticket t ON b.booking_id = t.booking_id  " +
            "             JOIN schedule s ON t.schedule_date_time = s.shedule_date_time AND t.room_id = s.room_id  " +
            "             JOIN room r on s.room_id = r.room_id  " +
            "             JOIN theater th on r.theater_id = th.theater_id  " +
            "             JOIN  province p on th.province_id = p.province_id  " +
            "    WHERE b.payment_status = true  " +
            "      AND p.province_id = :provinceId  " +
            "    GROUP BY DATE(b.created_at)  " +
            ") AS ticket_count ON calendar.date = ticket_count.booking_date  " +
            "ORDER BY calendar.date;", nativeQuery=true)
    List<TicketSoldDTO> ticketByPeriodTimeAndProvince(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("provinceId") String province);

    @Query(value="SELECT s.movie_id as movieId, m.movie_name as movieName, m.image_url as imageUrl, mt.move_type_name as movieType, SUM(t.price) AS revenue  " +
            "FROM ticket t  " +
            "         JOIN booking b ON t.booking_id = b.booking_id  " +
            "         JOIN schedule s on t.room_id = s.room_id and t.schedule_date_time = s.shedule_date_time  " +
            "         JOIN movie m on s.movie_id = m.movie_id  " +
            "         JOIN movie_type mt on m.movie_type_id = mt.movie_type_id  " +
            "     WHERE b.payment_status = true  " +
            "  AND MONTH(b.created_at) = month(:month) " +
            "  AND YEAR(b.created_at) =  year(:month ) " +
            "GROUP BY s.movie_id  " +
            "ORDER BY revenue DESC  " +
            "LIMIT 3;", nativeQuery=true)
    List<Top3MovieInMonth> top3MovieInMonth(@Param("month") LocalDate month);

    @Query(value="SELECT  th.theater_id as theaterId, th.theater_name as theaterName,p.province_name as province, SUM(t.price) AS revenue  " +
            "FROM ticket t  " +
            "         JOIN booking b ON t.booking_id = b.booking_id  " +
            "         JOIN schedule s on t.room_id = s.room_id and t.schedule_date_time = s.shedule_date_time  " +
            "         JOIN room r on s.room_id = r.room_id  " +
            "         JOIN theater th on r.theater_id = th.theater_id  " +
            "         JOIN province p on th.province_id = p.province_id  " +
            "     WHERE b.payment_status = true  " +
            "  AND MONTH(b.created_at) = month(:month) " +
            "  AND YEAR(b.created_at) = year(:month )  " +
            "GROUP BY th.theater_id  " +
            "ORDER BY revenue DESC  " +
            "LIMIT 5;", nativeQuery= true)
    List<Top5TheaterInMonth> top5TheaterInMonth(@Param("month") LocalDate month);
}
