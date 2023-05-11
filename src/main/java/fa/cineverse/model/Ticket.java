package fa.cineverse.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Ticket {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "TK"),
            strategy = "fa.cineverse.common.IdentityCodeGenerator")
	private String ticketId;
	
	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	 
	@ManyToOne
	@JoinColumns(value = { 
			@JoinColumn(name = "movie_id"),
			@JoinColumn(name = "room_id"),
			@JoinColumn(name = "schedule_date_time")
	})
	private Schedule schedule;
	
	@ManyToOne
	@JoinColumn
	private Seat seat;

	@ManyToOne
	@JoinColumn(name="booking_id")
	private Booking booking;

	public Ticket() {
		super();
	}

	public Ticket(String ticketId, boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt,
			Schedule schedule, Seat seat, Booking booking) {
		super();
		this.ticketId = ticketId;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.schedule = schedule;
		this.seat = seat;
		this.booking = booking;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
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


	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
}
