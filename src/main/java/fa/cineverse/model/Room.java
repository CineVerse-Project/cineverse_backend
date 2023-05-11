/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author HuuNQ
 *
 * 11 May 2023
 * 
 */
@Entity
public class Room {
	@Id
	private String roomId;
	
	private int seatTotal;
	
	private String roomName;
	
	private int seatRowNumber;
	
	private int seatColumnNumber;
	
	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn
	private Theater theater;
	
	@OneToMany(mappedBy = "room")
	private List<Seat> seats;
	
	@OneToMany(mappedBy = "room")
	private List<Schedule> schedules;
	
	public Room() {
		super();
	}

	public Room(String roomId, int seatTotal, String roomName, int seatRowNumber, int seatColumnNumber,
			boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.roomId = roomId;
		this.seatTotal = seatTotal;
		this.roomName = roomName;
		this.seatRowNumber = seatRowNumber;
		this.seatColumnNumber = seatColumnNumber;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getSeatTotal() {
		return seatTotal;
	}

	public void setSeatTotal(int seatTotal) {
		this.seatTotal = seatTotal;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getSeatRowNumber() {
		return seatRowNumber;
	}

	public void setSeatRowNumber(int seatRowNumber) {
		this.seatRowNumber = seatRowNumber;
	}

	public int getSeatColumnNumber() {
		return seatColumnNumber;
	}

	public void setSeatColumnNumber(int seatColumnNumber) {
		this.seatColumnNumber = seatColumnNumber;
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
	
	
}
