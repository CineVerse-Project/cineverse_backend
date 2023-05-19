package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Ticket;

public interface TicketService {

	List<Ticket> findAll();
	
	List<Ticket> saveAll(List<Ticket> ticket);
}
