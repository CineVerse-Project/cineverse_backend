package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {

}
