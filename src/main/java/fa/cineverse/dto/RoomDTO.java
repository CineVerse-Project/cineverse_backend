package fa.cineverse.dto;

import java.time.LocalDateTime;
import java.util.List;


import fa.cineverse.model.Schedule;
import fa.cineverse.model.Seat;
import fa.cineverse.model.Theater;

public class RoomDTO {

	private String roomId;

	private int seatTotal;

	private String roomName;

	private int seatRowNumber;

	private int seatColumnNumber;

	private boolean isDelete;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;


	private Theater theater;

	private List<Seat> seats;

	private List<Schedule> schedules;

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

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public RoomDTO(String roomId, int seatTotal, String roomName, int seatRowNumber, int seatColumnNumber,
			boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt, Theater theater, List<Seat> seats,
			List<Schedule> schedules) {
		super();
		this.roomId = roomId;
		this.seatTotal = seatTotal;
		this.roomName = roomName;
		this.seatRowNumber = seatRowNumber;
		this.seatColumnNumber = seatColumnNumber;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.theater = theater;
		this.seats = seats;
		this.schedules = schedules;
	}

	public RoomDTO() {
		super();
	}
	
	
}
