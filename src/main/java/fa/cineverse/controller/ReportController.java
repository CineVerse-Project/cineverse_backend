package fa.cineverse.controller;

import fa.cineverse.dto.*;
import fa.cineverse.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;


    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [month]
     */
    @GetMapping("/revenueByMonth")
    public ResponseEntity<?> revenueByMonth(@RequestParam("month") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate month) {
        LocalDate previousMonth = month.minusDays(1);
        LocalDate startOfMonth = month.withDayOfMonth(1);
        LocalDate endOfMonth = month.withDayOfMonth(month.lengthOfMonth());

        LocalDate startOfPreviousMonth = startOfMonth.minusMonths(1);
        LocalDate endOfPreviousMonth = endOfMonth.minusMonths(1);

        List<RevenueDTO> scheduleList = reportService.revenueByPeriodTime(startOfMonth, endOfMonth);
        List<RevenueDTO> schedulePeriodList = reportService.revenueByPeriodTime(startOfPreviousMonth, endOfPreviousMonth);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }


    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate]
     */
    @GetMapping("/revenueByPeriodTime")
    public ResponseEntity<?> revenueByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<RevenueDTO> scheduleList = reportService.revenueByPeriodTime(startDate, endDate);
        List<RevenueDTO> schedulePeriodList = reportService.revenueByPeriodTime(previousStartDate, previousEndDate);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }


    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, movieId]
     */
    @GetMapping("/revenueByPeriodTimeAndMovie")
    public ResponseEntity<?> revenueByPeriodTimeAndMovie(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                         @RequestParam("movieId") String movieId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<RevenueDTO> scheduleList = reportService.revenueByPeriodTimeAndMovie(startDate, endDate, movieId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueByPeriodTimeAndMovie(previousStartDate, previousEndDate, movieId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }


    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, theaterId]
     */
    @GetMapping("/revenueByPeriodTimeAndTheater")
    public ResponseEntity<?> revenueByPeriodTimeAndTheater(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                           @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                           @RequestParam("theaterId") String theaterId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<RevenueDTO> scheduleList = reportService.revenueByPeriodTimeAndTheater(startDate, endDate, theaterId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueByPeriodTimeAndTheater(previousStartDate, previousEndDate, theaterId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, provinceId]
     */

    @GetMapping("/revenueByPeriodTimeAndProvince")
    public ResponseEntity<?> revenueByPeriodTimeAndProvince(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                            @RequestParam("provinceId") String provinceId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<RevenueDTO> scheduleList = reportService.revenueByPeriodTimeAndProvince(startDate, endDate, provinceId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueByPeriodTimeAndProvince(previousStartDate, previousEndDate, provinceId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year]
     */
    @GetMapping("/revenueMonthInYear")
    public ResponseEntity<?> revenueMonthInYear(@RequestParam("year") String year) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);
        List<RevenueDTO> scheduleList = reportService.revenueMonthInYear(year);
        List<RevenueDTO> schedulePeriodList = reportService.revenueMonthInYear(lastYearString);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, movieId]
     */
    @GetMapping("/revenueMonthInYearAndMovie")
    public ResponseEntity<?> revenueMonthInYearAndMovie(@RequestParam("year") String year, @RequestParam("movieId") String movieId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);

        List<RevenueDTO> scheduleList = reportService.revenueMonthInYearAndMovie(year, movieId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueMonthInYearAndMovie(lastYearString, movieId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, theaterId]
     */
    @GetMapping("/revenueMonthInYearAndTheater")
    public ResponseEntity<?> revenueMonthInYearAndTheater(@RequestParam("year") String year, @RequestParam("theaterId") String theaterId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);
        List<RevenueDTO> scheduleList = reportService.revenueMonthInYearAndTheater(year, theaterId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueMonthInYearAndTheater(lastYearString, theaterId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:26 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, provinceId]
     */
    @GetMapping("/revenueMonthInYearAndProvince")
    public ResponseEntity<?> revenueMonthInYearAndProvince(@RequestParam("year") String year, @RequestParam("provinceId") String provinceId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);
        List<RevenueDTO> scheduleList = reportService.revenueMonthInYearAndProvince(year, provinceId);
        List<RevenueDTO> schedulePeriodList = reportService.revenueMonthInYearAndProvince(lastYearString, provinceId);
        ListReveneResponse reveneResponseList = new ListReveneResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(reveneResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:27 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year]
     */
    @GetMapping("/ticketMonthInYear")
    public ResponseEntity<?> ticketMonthInYear(@RequestParam("year") String year) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);
        List<TicketDTO> scheduleList = reportService.ticketMonthInYear(year);
        List<TicketDTO> schedulePeriodList = reportService.ticketMonthInYear(lastYearString);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:26 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, movieId]
     */
    @GetMapping("/ticketMonthInYearAndMovie")
    public ResponseEntity<?> ticketMonthInYearAndMovie(@RequestParam("year") String year, @RequestParam("movieId") String movieId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);

        List<TicketDTO> scheduleList = reportService.ticketMonthInYearAndMovie(year, movieId);
        List<TicketDTO> schedulePeriodList = reportService.ticketMonthInYearAndMovie(lastYearString, movieId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:26 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, theaterId]
     */
    @GetMapping("/ticketMonthInYearAndTheater")
    public ResponseEntity<?> ticketMonthInYearAndTheater(@RequestParam("year") String year, @RequestParam("theaterId") String theaterId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);

        List<TicketDTO> scheduleList = reportService.ticketMonthInYearAndTheater(year, theaterId);
        List<TicketDTO> schedulePeriodList = reportService.ticketMonthInYearAndTheater(lastYearString, theaterId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:26 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [year, provinceId]
     */
    @GetMapping("/ticketMonthInYearAndProvince")
    public ResponseEntity<?> ticketMonthInYearAndProvince(@RequestParam("year") String year, @RequestParam("provinceId") String provinceId) {
        int lastYear = Integer.parseInt(year) - 1;
        String lastYearString = Integer.toString(lastYear);

        List<TicketDTO> scheduleList = reportService.ticketMonthInYearAndProvince(year, provinceId);
        List<TicketDTO> schedulePeriodList = reportService.ticketMonthInYearAndProvince(lastYearString, provinceId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:26 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate]
     */
    @GetMapping("/ticketByPeriodTime")
    public ResponseEntity<?> ticketByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<TicketDTO> scheduleList = reportService.ticketByPeriodTime(startDate, endDate);
        List<TicketDTO> schedulePeriodList = reportService.ticketByPeriodTime(previousStartDate, previousEndDate);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }


    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:25 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, movieId]
     */
    @GetMapping("/ticketByPeriodTimeAndMovie")
    public ResponseEntity<?> ticketByPeriodTimeAndMovie(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                        @RequestParam("movieId") String movieId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<TicketDTO> scheduleList = reportService.ticketByPeriodTimeAndMovie(startDate, endDate, movieId);
        List<TicketDTO> schedulePeriodList = reportService.ticketByPeriodTimeAndMovie(previousStartDate, previousEndDate, movieId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:25 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, movieId]
     */
    @GetMapping("/ticketByPeriodTimeAndTheater")
    public ResponseEntity<?> ticketByPeriodTimeAndTheater(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                          @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                          @RequestParam("theaterId") String theaterId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<TicketDTO> scheduleList = reportService.ticketByPeriodTimeAndTheater(startDate, endDate, theaterId);
        List<TicketDTO> schedulePeriodList = reportService.ticketByPeriodTimeAndTheater(previousStartDate, previousEndDate, theaterId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }

    /**
     * @Author: AnP1
     * @Day: 5/26/2023 10:30 AM
     * @Return: org.springframework.http.ResponseEntity<?>
     * @Params: [startDate, endDate, provinceId]
     */
    @GetMapping("/ticketByPeriodTimeAndProvince")
    public ResponseEntity<?> ticketByPeriodTimeAndProvince(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                           @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                           @RequestParam("provinceId") String provinceId) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);

        List<TicketDTO> scheduleList = reportService.ticketByPeriodTimeAndProvince(startDate, endDate, provinceId);
        List<TicketDTO> schedulePeriodList = reportService.ticketByPeriodTimeAndProvince(previousStartDate, previousEndDate, provinceId);
        ListTicketResponse ticketResponseList = new ListTicketResponse(scheduleList, schedulePeriodList);
        return new ResponseEntity<>(ticketResponseList, HttpStatus.OK);
    }


    @GetMapping("/top3MovieInMonth")
    public ResponseEntity<?> top3MovieInMonth(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Top3MovieInMonth> top3MovieInMonthList = reportService.top3MovieInMonth(date);
        if(top3MovieInMonthList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(top3MovieInMonthList, HttpStatus.OK);
    }

    @GetMapping("/top5TheaterInMonth")
    public ResponseEntity<?> top5TheaterInMonth(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Top5TheaterInMonth> top5TheaterInMonthList = reportService.top5TheaterInMonth(date);
        if(top5TheaterInMonthList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(top5TheaterInMonthList, HttpStatus.OK);
    }

}
