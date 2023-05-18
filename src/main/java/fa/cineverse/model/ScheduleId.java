package fa.cineverse.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScheduleId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDateTime sheduleDateTime;
	
	
	private String roomId;
	
	public ScheduleId() {
		super();
	}

	public ScheduleId(LocalDateTime sheduleDateTime, String roomId) {
		super();
		this.setSheduleDateTime(sheduleDateTime);

		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}



	public LocalDateTime getSheduleDateTime() {
		return sheduleDateTime;
	}


	public void setSheduleDateTime(LocalDateTime sheduleDateTime) {
		this.sheduleDateTime = sheduleDateTime;
	}
	
	
}
