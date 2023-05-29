package fa.cineverse.service.impl;

import fa.cineverse.common.Constant;
import fa.cineverse.dto.ScheduleCheckDTO;
import fa.cineverse.dto.ScheduleDTO;
import fa.cineverse.model.*;
import fa.cineverse.repository.ScheduleRepository;
import fa.cineverse.repository.SeatRepository;
import fa.cineverse.repository.TicketRepository;
import fa.cineverse.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:42 AM
    */
    @Override
    public Page<Schedule> findAll(Pageable pageable, String keyword) {
        return scheduleRepository.findAll(pageable, keyword);
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:45 AM
    */
    @Override
    public Schedule findById(ScheduleId scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:48 AM
    */
    @Override
    public Schedule save(Schedule schedule) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        schedule.setCreatedAt(nowDateTime);

        String roomId = schedule.getScheduleId().getRoomId();
        List<Seat> seatList = seatRepository.findAllByRoom_RoomId(roomId);

        Schedule scheduleSaved = scheduleRepository.save(schedule);
        LocalDateTime now = LocalDateTime.now();

        Double price = Constant.TICKET_PRICE;
        seatList.forEach(seat -> {
            double increasePercent = 0.0;
            LocalDateTime scheduleDateTime = schedule.getScheduleId().getSheduleDateTime();
            String provinceId = seat.getRoom().getTheater().getProvince().getProvinceId();
            if ("PV-0001".equals(provinceId) || "PV-0002".equals(provinceId) || "PV-0003".equals(provinceId)) {
                increasePercent += 0.5;
            }
            if ("ST-0001".equals(seat.getSeatType().getSeatTypeId())) {
                increasePercent += 0.1;
            }
            DayOfWeek dayOfWeek = scheduleDateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY) ||
                    dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                increasePercent += 0.4;
            }
            double actualPrice = price*(increasePercent+1);
            Ticket ticketCreate = new Ticket();
            ticketCreate.setSchedule(scheduleSaved);
            ticketCreate.setCreatedAt(now);
            ticketCreate.setSeat(seat);
            ticketCreate.setPrice(actualPrice);
            ticketRepository.save(ticketCreate);
        });
        return scheduleSaved;
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:52 AM
    */
    @Override
    public Schedule update(Schedule schedule) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        schedule.setUpdatedAt(nowDateTime);

        String roomId = schedule.getScheduleId().getRoomId();

        return scheduleRepository.save(schedule);
    }

    /**
     * @Author: AnP1
     * @Day: May 23, 2023 | @Time: 9:04:55 AM
    */
    @Override
    public void remove(LocalDateTime scheduleDateTime, String roomId) {
        scheduleRepository.removeAndUpdate(scheduleDateTime, roomId);
    }

    @Override
    public List<ScheduleCheckDTO> check(ScheduleDTO scheduleDTO, Movie movie) {
        LocalDateTime startDateTime = scheduleDTO.getScheduleId().getSheduleDateTime();
        Float duration =  movie.getDuration();
        LocalDateTime endDateTime = startDateTime.plusMinutes(duration.longValue());
        String roomId = scheduleDTO.getScheduleId().getRoomId();
        List<ScheduleCheckDTO>  list = scheduleRepository.checkSchedule(startDateTime,endDateTime,roomId);
        System.out.println(list.size());
        return list;
    }

}
