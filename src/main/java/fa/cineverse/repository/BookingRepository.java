package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
