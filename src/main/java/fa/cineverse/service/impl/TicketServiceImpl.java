package fa.cineverse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Ticket;
import fa.cineverse.repository.TicketRepository;
import fa.cineverse.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	/**
	 * @Author: HuongNT106
	 * @Day: May 19, 2023 | @Time: 9:56:08 AM
	 * TODO
	 */
	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> saveAll(List<Ticket> tickets) {
		// TODO Auto-generated method stub
		return ticketRepository.saveAll(tickets);
	}

}
