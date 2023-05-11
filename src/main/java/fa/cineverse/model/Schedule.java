/**
 * Created At {11 May 2023
 * By HuuNQ
 */
package fa.cineverse.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

/**
 * @author HuuNQ
 *
 * 11 May 2023
 * 
 */
@Entity
public class Schedule {
	
	@EmbeddedId
	private  ScheduleId scheduleId;
	
	@ManyToOne
	@MapsId("movieId")
	@JoinColumn
	private Movie movie;
	
	@ManyToOne
	@MapsId("roomId")
	@JoinColumn
	private Room room;
	
	private boolean isDelete;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "schedule")
	private List<Ticket> tickets;

	public Schedule() {
		super();
	}

	public Schedule(ScheduleId scheduleId, boolean isDelete, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.scheduleId = scheduleId;
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
	
	
}
