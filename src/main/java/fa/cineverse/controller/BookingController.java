package fa.cineverse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.model.Booking;
import fa.cineverse.model.Customer;
import fa.cineverse.service.BookingService;
import fa.cineverse.service.CustomerService;

/**
* BookingController
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
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    /**
     * find all
     * 
     * @return: ResponseEntity<List<Booking>>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:20:55 AM
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
     * create booking
     * @param: 
     * @return: ResponseEntity<?>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:21:59 AM
     */
    @PostMapping("")
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> createBooking(Authentication currentUser) {
        Customer customer = customerService.findCustomerByUser(currentUser.getName());
        Booking booking = new Booking();
        booking.setCustomer(customer);
        Booking bookingSave = bookingService.save(booking);
        return new ResponseEntity<>(bookingSave, HttpStatus.OK);
    }

    /**
     * change Payment Status
     * @param: id
     * @return: ResponseEntity<Booking>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:22:04 AM
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

    /**
     * find Customer By Current User
     * @param: 
     * @return: ResponseEntity<Customer>
     * @throws:
     * @Author: HuongNT106
     * @Day: May 30, 2023 | @Time: 11:22:42 AM
     */
    @GetMapping("customer")
    @Secured({"ROLE_USER"})
    public ResponseEntity<Customer> findCustomerByCurrentUser(Authentication currentUser){
        Customer customer = customerService.findCustomerByUser(currentUser.getName());
        if (customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
