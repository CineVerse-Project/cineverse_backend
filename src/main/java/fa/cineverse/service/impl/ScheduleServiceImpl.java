package fa.cineverse.service.impl;

import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.model.Seat;
import fa.cineverse.model.Ticket;
import fa.cineverse.repository.ScheduleRepository;
import fa.cineverse.repository.SeatRepository;
import fa.cineverse.repository.TicketRepository;
import fa.cineverse.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Page<Schedule> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return scheduleRepository.findAll(pageable);
	}

	@Override
	public Schedule findById(ScheduleId scheduleId) {
		return scheduleRepository.findById(scheduleId).orElse(null);
	}

	@Override
	public Schedule save(Schedule schedule) {
		LocalDateTime nowDateTime = LocalDateTime.now();
		schedule.setCreatedAt(nowDateTime);

		String roomId = schedule.getScheduleId().getRoomId();
		List<Seat> seatList = seatRepository.findAllByRoom_RoomId(roomId);

		Schedule scheduleSaved = scheduleRepository.save(schedule);
		LocalDateTime now = LocalDateTime.now();
		seatList.forEach(seat -> {
			Ticket ticketCreate = new Ticket();
			ticketCreate.setSchedule(scheduleSaved);
			ticketCreate.setCreatedAt(now);
			ticketCreate.setTicketId(seat.getSeatRoomId());
			ticketRepository.save(ticketCreate);
		});
		return scheduleSaved;
	}

	@Override
	public Schedule update(Schedule schedule) {
		LocalDateTime nowDateTime = LocalDateTime.now();
		schedule.setUpdatedAt(nowDateTime);

		String roomId = schedule.getScheduleId().getRoomId();

		return scheduleRepository.save(schedule);
	}

	@Override
	public void remove(LocalDateTime scheduleDateTime, String roomId) {
		scheduleRepository.removeAndUpdate(scheduleDateTime,roomId);
	}

}
