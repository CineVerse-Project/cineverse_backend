package fa.cineverse.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import fa.cineverse.model.Province;
import fa.cineverse.model.Room;

public class TheaterDTO {

	private String theaterId;

	@NotNull
	@Column(unique = true)
	private String theaterName;

	private String theaterAddress;

	private boolean isDelete;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Province province;

	private List<Room> room;

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterAddress() {
		return theaterAddress;
	}

	public void setTheaterAddress(String theaterAddress) {
		this.theaterAddress = theaterAddress;
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	public TheaterDTO(String theaterId, @NotNull String theaterName, String theaterAddress, boolean isDelete,
			LocalDateTime createdAt, LocalDateTime updatedAt, Province province, List<Room> room) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterAddress = theaterAddress;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.province = province;
		this.room = room;
	}

	public TheaterDTO() {
		super();
	}
	
	
}
