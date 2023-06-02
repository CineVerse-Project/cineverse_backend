package fa.cineverse.dto;

import java.util.List;

import fa.cineverse.model.Customer;
import fa.cineverse.model.Payment;
import fa.cineverse.model.Ticket;

public class BookingDTO {
	private String bookingId;
	
	private int ticketTotal;
	
	private boolean isDelete;
	
	private Customer customer;
	
	private Payment payment;
	
	private List<Ticket> tickets;

	public BookingDTO() {
		super();
	}

	public BookingDTO(String bookingId, int ticketTotal, boolean isDelete, Customer customer, Payment payment,
			List<Ticket> tickets) {
		super();
		this.bookingId = bookingId;
		this.ticketTotal = ticketTotal;
		this.isDelete = isDelete;
		this.customer = customer;
		this.payment = payment;
		this.tickets = tickets;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public int getTicketTotal() {
		return ticketTotal;
	}

	public void setTicketTotal(int ticketTotal) {
		this.ticketTotal = ticketTotal;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
