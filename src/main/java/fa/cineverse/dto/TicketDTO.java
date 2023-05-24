package fa.cineverse.dto;

import java.time.LocalDateTime;

import fa.cineverse.model.Booking;
import fa.cineverse.model.Schedule;
import fa.cineverse.model.Seat;

public class TicketDTO {
	private String ticketId;

	private boolean isDelete;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private Schedule schedule;
	
	private Seat seat;
	
	private Booking booking;

	public TicketDTO() {
		super();
	}

	public TicketDTO(String ticketId, boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt,
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
}
