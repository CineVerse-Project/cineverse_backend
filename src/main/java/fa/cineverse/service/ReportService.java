package fa.cineverse.service;

import fa.cineverse.dto.*;
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

    List<TicketSoldDTO> ticketMonthInYear(@Param("year") String year);

    List<TicketSoldDTO> ticketMonthInYearAndMovie( String year,  String movieId);

    List<TicketSoldDTO> ticketMonthInYearAndTheater(String year, String theaterId);

    List<TicketSoldDTO> ticketMonthInYearAndProvince( String year,  String provinceId);

    List<TicketSoldDTO> ticketByPeriodTime(LocalDate startDate,  LocalDate endDate);

    List<TicketSoldDTO> ticketByPeriodTimeAndMovie(LocalDate startDate,  LocalDate endDate, String movieId);

    List<TicketSoldDTO> ticketByPeriodTimeAndTheater( LocalDate startDate, LocalDate endDate,  String theater);

    List<TicketSoldDTO> ticketByPeriodTimeAndProvince( LocalDate startDate,  LocalDate endDate,  String province);

    List<Top3MovieInMonth> top3MovieInMonth( LocalDate month);
    List<Top5TheaterInMonth> top5TheaterInMonth(LocalDate month);

}
