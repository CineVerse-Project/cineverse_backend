package fa.cineverse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.ScheduleId;
import fa.cineverse.model.Ticket;
import fa.cineverse.service.TicketService;

/**
* TicketController
*
* Version: 1.0
*
* Date: May 30, 2023
*
* Copyright
*
* Modification Log:
*
* DATE          AUTHOR          DESCRIPTION 
* -----------------------------------------
* May 30, 2023       HuongNT106          
*
*/
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * find all
     * 
     * @return: ResponseEntity<List<Ticket>>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:25:40 AM
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
     * find ticket by schedule id
     * @param: roomId
     * @param: sheduleDateTime
     * @return: ResponseEntity<List<Ticket>>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:26:14 AM
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

    /**
     * save ticket
     * @param: bookingId
     * @param: ticketDTOList
     * @return: ResponseEntity<Void>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:26:56 AM
     */
    @PatchMapping("")
    public ResponseEntity<Void> saveTicket(@RequestParam("bookingId") String bookingId, @RequestBody List<TicketDTO> ticketDTOList) {
        ticketService.save(ticketDTOList, bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find ticket by booking id
     * @param: bookingId
     * @return: ResponseEntity<List<Ticket>>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:27:22 AM
     */
    @GetMapping("/detailBooking")
    public ResponseEntity<List<Ticket>> findTicketByBookingId(@RequestParam("bookingId") String bookingId) {
        List<Ticket> tickets = ticketService.findTicketByBookingId(bookingId);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * find ticket by ticket id
     * @param: id
     * @return: ResponseEntity<Ticket>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:27:51 AM
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findTicketByTicketId(@PathVariable("id") String id) {

        Ticket ticket = ticketService.findById(id);
        if (ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
