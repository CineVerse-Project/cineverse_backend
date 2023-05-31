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

/**
 * ScheduleServiceImpl
 *
 * @Day: 5/30/2023 9:09 AM
 *
 * @Version 1.0
 *
 * Copyright
 *
 * Modification Logs:
 * DATE          AUTHOR       DESCRIPTION
 * ---------------------------------------
 * 5/29/2023      AnP1          Create
 */
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
     * @Day: 5/30/2023 9:30 AM
     * @Return: org.springframework.data.domain.Page<fa.cineverse.model.Schedule>
     * @Params: [pageable, keyword]
     */
    @Override
    public Page<Schedule> findAll(Pageable pageable, String keyword) {
        return scheduleRepository.findAll(pageable, keyword);
    }


    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:30 AM
     * @Return: fa.cineverse.model.Schedule
     * @Params: [scheduleId]
     */
    @Override
    public Schedule findById(ScheduleId scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }


    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:30 AM
     * @Return: fa.cineverse.model.Schedule
     * @Params: [schedule]
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
            double actualPrice = price * (increasePercent + 1);
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
     * @Day: 5/30/2023 9:30 AM
     * @Return: fa.cineverse.model.Schedule
     * @Params: [schedule]
     */
    @Override
    public Schedule update(Schedule schedule) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        schedule.setUpdatedAt(nowDateTime);
        return scheduleRepository.save(schedule);
    }


    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:31 AM
     * @Return: void
     * @Params: [scheduleDateTime, roomId]
     */
    @Override
    public void remove(LocalDateTime scheduleDateTime, String roomId) {
        scheduleRepository.removeAndUpdate(scheduleDateTime, roomId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:31 AM
     * @Return: java.util.List<fa.cineverse.dto.ScheduleCheckDTO>
     * @Params: [scheduleDTO, movie]
     */
    @Override
    public List<ScheduleCheckDTO> check(ScheduleDTO scheduleDTO, Movie movie) {
        LocalDateTime startDateTime = scheduleDTO.getScheduleId().getSheduleDateTime();
        Float duration = movie.getDuration();
        LocalDateTime endDateTime = startDateTime.plusMinutes(duration.longValue());
        String roomId = scheduleDTO.getScheduleId().getRoomId();
        return scheduleRepository.checkSchedule(startDateTime, endDateTime, roomId);
    }

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:34:21 PM
     * TODO
     */
    @Override
    public List<Schedule> findScheduleByMovieAndScheduleAndProvince(String movieId, LocalDateTime scheduleDateTime, String provinceId) {
        if ("".equals(provinceId)) {
            return scheduleRepository.findScheduleByMovieAndSchedule(movieId, scheduleDateTime);
        }
        return scheduleRepository.findScheduleByMovieAndScheduleAndProvince(movieId, scheduleDateTime, provinceId);
    }
}
