package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findAllByRoom_RoomId(String roomId);
}
