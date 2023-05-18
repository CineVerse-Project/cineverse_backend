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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"tickets"})
public class Seat {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "SE"),
            strategy = "fa.cineverse.common.IdentityCodeGenerator")
	private String seatRoomId;
	
	private String seatName;
	
	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="seat_type_id")
	private SeatType seatType;
	
	@ManyToOne
	@JoinColumn(name="room_id") 
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
