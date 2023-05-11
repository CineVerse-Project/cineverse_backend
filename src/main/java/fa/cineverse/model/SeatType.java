package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SeatType {
	@Id
	private String seatTypeId;
	
	private String seatTypeName;

	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "seatType")
	private List<Seat> seats;

	public SeatType() {
		super();
	}
	
	public SeatType(String seatTypeId, String seatTypeName, boolean isDelete, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.seatTypeId = seatTypeId;
		this.seatTypeName = seatTypeName;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getSeatTypeId() {
		return seatTypeId;
	}

	public void setSeatTypeId(String seatTypeId) {
		this.seatTypeId = seatTypeId;
	}

	public String getSeatTypeName() {
		return seatTypeName;
	}

	public void setSeatTypeName(String seatTypeName) {
		this.seatTypeName = seatTypeName;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
}
