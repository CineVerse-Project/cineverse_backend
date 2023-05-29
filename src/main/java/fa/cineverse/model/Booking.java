package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"payment", "tickets"})
public class Booking {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	@GeneratedValue(generator = "prod-generator")
	@GenericGenerator(name = "prod-generator",
			parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "BK"),
			strategy = "fa.cineverse.common.IdentityCodeGenerator")
	private String bookingId;

	private int ticketTotal;

	private boolean isDelete;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(mappedBy = "booking")
	private Payment payment;

	@OneToMany(mappedBy = "booking")
	private List<Ticket> tickets;

	private boolean paymentStatus;

	public Booking() {
		super();
	}

	public Booking(String bookingId, int ticketTotal, boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt, Customer customer, Payment payment, List<Ticket> tickets, boolean paymentStatus) {
		this.bookingId = bookingId;
		this.ticketTotal = ticketTotal;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.customer = customer;
		this.payment = payment;
		this.tickets = tickets;
		this.paymentStatus = paymentStatus;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}