package fa.cineverse.service;

import fa.cineverse.dto.RevenueDTO;
import fa.cineverse.dto.TicketDTO;
import fa.cineverse.dto.Top3MovieInMonth;
import fa.cineverse.dto.Top5TheaterInMonth;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<RevenueDTO> revenueByMonth(LocalDate startDateTime);

    List<RevenueDTO> revenueByPeriodTime(LocalDate startDate, LocalDate endDate);

    List<RevenueDTO> revenueByPeriodTimeAndMovie(LocalDate startDate, LocalDate endDate, String movieId);

    List<RevenueDTO> revenueByPeriodTimeAndTheater(LocalDate startDate, LocalDate endDate, String theaterId);

    List<RevenueDTO> revenueByPeriodTimeAndProvince(LocalDate startDate, LocalDate endDate, String province);

    List<RevenueDTO> revenueMonthInYear(String year);

    List<RevenueDTO> revenueMonthInYearAndMovie(String year, String movieId);

    List<RevenueDTO> revenueMonthInYearAndTheater(String year, String theaterId);

    List<RevenueDTO> revenueMonthInYearAndProvince(String year, String provinceId);

    List<TicketDTO> ticketMonthInYear(@Param("year") String year);

    List<TicketDTO> ticketMonthInYearAndMovie( String year,  String movieId);

    List<TicketDTO> ticketMonthInYearAndTheater(String year, String theaterId);

    List<TicketDTO> ticketMonthInYearAndProvince( String year,  String provinceId);

    List<TicketDTO> ticketByPeriodTime(LocalDate startDate,  LocalDate endDate);

    List<TicketDTO> ticketByPeriodTimeAndMovie(LocalDate startDate,  LocalDate endDate, String movieId);

    List<TicketDTO> ticketByPeriodTimeAndTheater( LocalDate startDate, LocalDate endDate,  String theater);

    List<TicketDTO> ticketByPeriodTimeAndProvince( LocalDate startDate,  LocalDate endDate,  String province);

    List<Top3MovieInMonth> top3MovieInMonth( LocalDate month);
    List<Top5TheaterInMonth> top5TheaterInMonth(LocalDate month);

}
