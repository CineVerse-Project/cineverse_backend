package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, String> {

}
