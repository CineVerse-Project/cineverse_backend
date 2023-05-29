package fa.cineverse.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Schedule;
import fa.cineverse.model.ScheduleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fa.cineverse.model.Booking;
import fa.cineverse.model.Ticket;
import fa.cineverse.service.BookingService;
import fa.cineverse.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:32:43 PM
     * @Return: ResponseEntity<List < Ticket>>
     */
    @GetMapping("")
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = ticketService.findAll();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
    }

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:33:02 PM
     * @Return: ResponseEntity<List < Ticket>>
     */
    @GetMapping("/detail")
    public ResponseEntity<List<Ticket>> findTicketByScheduleId(@RequestParam String roomId,
                                                               @RequestParam("sheduleDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime sheduleDateTime) {
        ScheduleId scheduleId = new ScheduleId(sheduleDateTime, roomId);
        List<Ticket> tickets = ticketService.findTicketByScheduleScheduleId(scheduleId);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<Void> saveTicket(@RequestParam("bookingId") String bookingId, @RequestBody List<TicketDTO> ticketDTOList) {
        ticketService.save(ticketDTOList, bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detailBooking")
    public ResponseEntity<List<Ticket>> findTicketByBookingId(@RequestParam("bookingId") String bookingId) {
        List<Ticket> tickets = ticketService.findTicketByBookingId(bookingId);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findTicketByTicketId(@PathVariable("id") String id) {

        Ticket ticket = ticketService.findById(id);
        if (ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
