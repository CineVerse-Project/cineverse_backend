package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, String> {

}
