package fa.cineverse.controller;

import java.time.LocalDateTime;
import java.util.List;

import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.model.Booking;
import fa.cineverse.model.Ticket;
import fa.cineverse.service.BookingService;
import fa.cineverse.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

    @GetMapping("")
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = ticketService.findAll(); 
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tickets,HttpStatus.OK);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> findTicketByScheduleId(@RequestParam String roomId, @RequestParam String seatRoomId,
                                      @RequestParam("sheduleDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime sheduleDateTime ) {
        ScheduleId scheduleId = new ScheduleId(sheduleDateTime, roomId);
        System.out.println(scheduleId);
        Ticket ticket = ticketService.findTicketByScheduleScheduleIdAndSeat_SeatRoomId(scheduleId, seatRoomId);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


}
