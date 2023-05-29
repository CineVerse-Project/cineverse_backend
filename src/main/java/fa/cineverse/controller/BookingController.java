package fa.cineverse.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fa.cineverse.dto.TicketDTO;
import fa.cineverse.model.Customer;
import fa.cineverse.model.Ticket;
import fa.cineverse.service.CustomerService;
import fa.cineverse.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import fa.cineverse.model.Booking;
import fa.cineverse.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:30:54 PM
     * @Return: ResponseEntity<List<Booking>>
     */
    @GetMapping("")
    public ResponseEntity<List<Booking>> findAll() {
        List<Booking> bookings = bookingService.findAll();
        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
    }

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:31:01 PM
     * @Return: ResponseEntity<?>
     */
    @PostMapping("")
    public ResponseEntity<?> createBooking() {
//        Map<String, String> errorMap = new HashMap<>();
        Customer customer = customerService.findCustomerByUser("huonghuong");
        Booking booking = new Booking();
        booking.setCustomer(customer);
//        ticketDTOList.forEach(ticketDTO -> {
//            Ticket ticket = ticketService.findById(ticketDTO.getTicketId());
//            if (ticket == null) {
//                errorMap.put(ticketDTO.getTicketId(), "Ticket " + ticketDTO.getTicketId() + " is not exist");
//            } else {
//                if (ticket.isBooked()) {
//                    errorMap.put(ticketDTO.getTicketId(), "Ticket " + ticketDTO.getTicketId() + " has been already booked");
//                }
//                if (ticket.getSchedule().getScheduleId().getSheduleDateTime().isBefore(LocalDateTime.now())){
//                    errorMap.put(ticketDTO.getTicketId(), "The showtime will start shortly, please book ticket at the site you want to see the movie.");
//                }
//            }
//        });
//        if(!errorMap.isEmpty()){
//            return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
//        }
        Booking bookingSave = bookingService.save(booking);
        return new ResponseEntity<>(bookingSave, HttpStatus.OK);
    }

    /**
     * @Author: HuongNT106
     * @Day: May 26, 2023 | @Time: 11:31:30 PM
     * @Return: ResponseEntity<Booking>
     */
    @PatchMapping(value = "/changePaymentStatus")
    public ResponseEntity<Booking> changePaymentStatus(@RequestParam("id") String id) {
        Booking booking = bookingService.findById(id).orElse(null);
        if (booking == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Booking updateBooking = bookingService.changePaymentStatus(id);
        return new ResponseEntity<>(updateBooking, HttpStatus.OK);
    }

    @GetMapping("customer")
    public ResponseEntity<Customer> findCustomerByCurrentUser(){
        Customer customer = customerService.findCustomerByUser("huonghuong");
        if (customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
