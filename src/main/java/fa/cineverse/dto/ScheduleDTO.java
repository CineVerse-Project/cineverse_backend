package fa.cineverse.dto;

import fa.cineverse.model.Movie;
import fa.cineverse.model.Room;
import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ScheduleDTO implements Validator {
    private ScheduleId scheduleId;
	private Movie movie;
    private Room room;
    private boolean isDelete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


	public ScheduleDTO() {
		super();
	}
	public ScheduleDTO(ScheduleId scheduleId, Movie movie, Room room, boolean isDelete, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.scheduleId = scheduleId;
		this.movie = movie;
		this.room = room;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ScheduleId getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(ScheduleId scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
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


	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ScheduleDTO scheduleDTO = (ScheduleDTO) target;
		if(scheduleDTO.getScheduleId() == null){
			errors.rejectValue("scheduleId","","Schedule ID is required");
		} else {
			if(scheduleDTO.getScheduleId().getRoomId() == null){
				errors.rejectValue("scheduleId.roomId","","Room ID is required");
			}
			if(scheduleDTO.getScheduleId().getSheduleDateTime() == null){
				errors.rejectValue("scheduleId.sheduleDateTime","","Schedule Date Time is require");
			}
		}

		if(scheduleDTO.getMovie() == null){
			errors.rejectValue("movie","","Movie ID is required");
		}
	}
}
