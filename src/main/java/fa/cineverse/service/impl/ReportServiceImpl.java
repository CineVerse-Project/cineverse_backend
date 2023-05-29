package fa.cineverse.service.impl;

import fa.cineverse.dto.RevenueDTO;
import fa.cineverse.dto.TicketDTO;
import fa.cineverse.dto.Top3MovieInMonth;
import fa.cineverse.dto.Top5TheaterInMonth;
import fa.cineverse.repository.ReportRepository;
import fa.cineverse.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    /**
     * @Author: AnP1
     * @Day: May 26, 2023 | @Time: 10:27:51 PM
    */
    @Override
    public List<RevenueDTO> revenueByMonth(LocalDate month) {
        return reportRepository.revenueByMonth(month);
    }

    /**
     * @Author: AnP1
     * @Day: May 26, 2023 | @Time: 10:27:55 PM
    */
    @Override
    public List<RevenueDTO> revenueByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepository.revenueByPeriodTime(startDate,endDate);
    }

    /**
     * @Author: AnP1
     * @Day: May 26, 2023 | @Time: 10:27:57 PM
    */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndMovie(LocalDate startDate, LocalDate endDate, String movieId) {
        return reportRepository.revenueByPeriodTimeAndMovie(startDate,endDate,movieId);
    }

    /**
     * @Author: AnP1
     * @Day: May 26, 2023 | @Time: 10:28:00 PM
    */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndTheater(LocalDate startDate, LocalDate endDate, String theaterId) {
        return reportRepository.revenueByPeriodTimeAndTheater(startDate,endDate,theaterId);
    }

    /**
     * @Author: AnP1
     * @Day: May 26, 2023 | @Time: 10:28:02 PM
    */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndProvince(LocalDate startDate, LocalDate endDate, String province) {
        return reportRepository.revenueByPeriodTimeAndProvince(startDate,endDate,province);
    }

    @Override
    public List<RevenueDTO> revenueMonthInYear(String year) {
        return reportRepository.revenueMonthInYear(year);
    }

    @Override
    public List<RevenueDTO> revenueMonthInYearAndMovie(String year, String movieId) {
        return reportRepository.revenueMonthInYearAndMovie(year,movieId);
    }

    @Override
    public List<RevenueDTO> revenueMonthInYearAndTheater(String year, String theaterId) {
        return reportRepository.revenueMonthInYearAndTheater(year, theaterId);
    }

    @Override
    public List<RevenueDTO> revenueMonthInYearAndProvince(String year, String provinceId) {
        return reportRepository.revenueMonthInYearAndProvince(year, provinceId);
    }

    @Override
    public List<TicketDTO> ticketMonthInYear(String year) {
        return reportRepository.ticketMonthInYear(year);
    }

    @Override
    public List<TicketDTO> ticketMonthInYearAndMovie(String year, String movieId) {
        return reportRepository.ticketMonthInYearAndMovie(year,movieId);
    }

    @Override
    public List<TicketDTO> ticketMonthInYearAndTheater(String year, String theaterId) {
        return reportRepository.ticketMonthInYearAndTheater(year,theaterId);
    }

    @Override
    public List<TicketDTO> ticketMonthInYearAndProvince(String year, String provinceId) {
        return reportRepository.ticketMonthInYearAndProvince(year,provinceId);
    }

    @Override
    public List<TicketDTO> ticketByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepository.ticketByPeriodTime(startDate, endDate);
    }

    @Override
    public List<TicketDTO> ticketByPeriodTimeAndMovie(LocalDate startDate, LocalDate endDate, String movieId) {
        return reportRepository.ticketByPeriodTimeAndMovie(startDate, endDate,movieId);

    }

    @Override
    public List<TicketDTO> ticketByPeriodTimeAndTheater(LocalDate startDate, LocalDate endDate, String theater) {
        return reportRepository.ticketByPeriodTimeAndTheater(startDate, endDate,theater);

    }

    @Override
    public List<TicketDTO> ticketByPeriodTimeAndProvince(LocalDate startDate, LocalDate endDate, String province) {
        return reportRepository.ticketByPeriodTimeAndProvince(startDate, endDate,province);

    }

    @Override
    public List<Top3MovieInMonth> top3MovieInMonth(LocalDate month) {
        return reportRepository.top3MovieInMonth(month);
    }

    @Override
    public List<Top5TheaterInMonth> top5TheaterInMonth(LocalDate month) {
        return reportRepository.top5TheaterInMonth(month);
    }
}
