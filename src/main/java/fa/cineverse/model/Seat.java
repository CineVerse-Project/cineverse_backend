package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Seat {
	@Id
	private String seatRoomId;
	
	private String seatName;
	
	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn
	private SeatType seatType;
	
	@ManyToOne
	@JoinColumn
	private Room room;
	
	@OneToMany(mappedBy = "seat")
	private List<Ticket> tickets;

	public Seat() {
		super();
	}

	public Seat(String seatRoomId, String seatName, boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt,
			SeatType seatType, Room room) {
		super();
		this.seatRoomId = seatRoomId;
		this.seatName = seatName;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.seatType = seatType;
		this.room = room;
	}

	public String getSeatRoomId() {
		return seatRoomId;
	}

	public void setSeatRoomId(String seatRoomId) {
		this.seatRoomId = seatRoomId;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
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

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	
	
}
