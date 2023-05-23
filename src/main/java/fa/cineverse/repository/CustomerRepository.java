package fa.cineverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.cineverse.model.Customer;
import fa.cineverse.model.User;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByUser(User username);
	
	@Query(value = "select b.booking_id,b.created_at,\r\n"
			+ "m.movie_name,\r\n"
			+ "s.shedule_date_time,t.seat_id,\r\n"
			+ "th.theater_name,group_concat(t.seat_id),p.total_money,m.image_url \r\n"
			+ "from booking b \r\n"
			+ "inner join ticket t on b.booking_id = t.booking_id\r\n"
			+ "inner join `schedule`s on t.schedule_date_time = s.shedule_date_time\r\n"
			+ "inner join movie m on s.movie_id = m.movie_id\r\n"
			+ "inner join room r on s.room_id = r.room_id\r\n"
			+ "inner join theater th on r.theater_id = th.theater_id\r\n"
			+ "inner join payment p on b.booking_id = p.booking_id\r\n"
			+ "where customer_id = :customerId group by b.booking_id",
			countQuery = "select b.booking_id,b.created_at,\r\n"
					+ "m.movie_name,\r\n"
					+ "s.shedule_date_time,t.seat_id,\r\n"
					+ "th.theater_name,group_concat(t.seat_id),p.total_money,m.image_url \r\n"
					+ "from booking b \r\n"
					+ "inner join ticket t on b.booking_id = t.booking_id\r\n"
					+ "inner join `schedule`s on t.schedule_date_time = s.shedule_date_time\r\n"
					+ "inner join movie m on s.movie_id = m.movie_id\r\n"
					+ "inner join room r on s.room_id = r.room_id\r\n"
					+ "inner join theater th on r.theater_id = th.theater_id\r\n"
					+ "inner join payment p on b.booking_id = p.booking_id\r\n"
					+ "where customer_id = :customerId group by b.booking_id",nativeQuery = true)
	List listHistoryOrderByUsername(@Param("customerId") Integer customerId);
	
	
	@Query(value = "select b.booking_id,b.created_at,\r\n"
			+ "m.movie_name,\r\n"
			+ "s.shedule_date_time,t.seat_id,\r\n"
			+ "th.theater_name,group_concat(t.seat_id),p.total_money,(0.05 * (p.total_money/1000)) as earn_points \r\n"
			+ "from booking b \r\n"
			+ "inner join ticket t on b.booking_id = t.booking_id\r\n"
			+ "inner join `schedule`s on t.schedule_date_time = s.shedule_date_time\r\n"
			+ "inner join movie m on s.movie_id = m.movie_id\r\n"
			+ "inner join room r on s.room_id = r.room_id\r\n"
			+ "inner join theater th on r.theater_id = th.theater_id\r\n"
			+ "inner join payment p on b.booking_id = p.booking_id\r\n"
			+ "where customer_id = :customerId group by b.booking_id",
			countQuery = "select b.booking_id,b.created_at,\r\n"
					+ "m.movie_name,\r\n"
					+ "s.shedule_date_time,t.seat_id,\r\n"
					+ "th.theater_name,group_concat(t.seat_id),p.total_money,(0.05 * (p.total_money/1000)) as earn_points \r\n"
					+ "from booking b \r\n"
					+ "inner join ticket t on b.booking_id = t.booking_id\r\n"
					+ "inner join `schedule`s on t.schedule_date_time = s.shedule_date_time\r\n"
					+ "inner join movie m on s.movie_id = m.movie_id\r\n"
					+ "inner join room r on s.room_id = r.room_id\r\n"
					+ "inner join theater th on r.theater_id = th.theater_id\r\n"
					+ "inner join payment p on b.booking_id = p.booking_id\r\n"
					+ "where customer_id = :customerId group by b.booking_id",nativeQuery = true)
	List listEarnPointsByUsername(@Param("customerId") Integer customerId);
}