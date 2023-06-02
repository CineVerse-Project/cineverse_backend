package fa.cineverse.service.impl;

import fa.cineverse.dto.RevenueDTO;
import fa.cineverse.dto.TicketSoldDTO;
import fa.cineverse.dto.Top3MovieInMonth;
import fa.cineverse.dto.Top5TheaterInMonth;
import fa.cineverse.repository.ReportRepository;
import fa.cineverse.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * ScheduleServiceImpl
 *
 * @Day: 5/30/2023 9:09 AM
 * @Version 1.0
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE          AUTHOR       DESCRIPTION
 * ---------------------------------------
 * 5/29/2023      AnP1          Create
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;


    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:34 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [month]
     */
    @Override
    public List<RevenueDTO> revenueByMonth(LocalDate month) {
        return reportRepository.revenueByMonth(month);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:34 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [startDate, endDate]
     */
    @Override
    public List<RevenueDTO> revenueByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepository.revenueByPeriodTime(startDate, endDate);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [startDate, endDate, movieId]
     */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndMovie(LocalDate startDate, LocalDate endDate, String movieId) {
        return reportRepository.revenueByPeriodTimeAndMovie(startDate, endDate, movieId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [startDate, endDate, theaterId]
     */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndTheater(LocalDate startDate, LocalDate endDate, String theaterId) {
        return reportRepository.revenueByPeriodTimeAndTheater(startDate, endDate, theaterId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [startDate, endDate, province]
     */
    @Override
    public List<RevenueDTO> revenueByPeriodTimeAndProvince(LocalDate startDate, LocalDate endDate, String province) {
        return reportRepository.revenueByPeriodTimeAndProvince(startDate, endDate, province);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [year]
     */
    @Override
    public List<RevenueDTO> revenueMonthInYear(String year) {
        return reportRepository.revenueMonthInYear(year);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [year, movieId]
     */
    @Override
    public List<RevenueDTO> revenueMonthInYearAndMovie(String year, String movieId) {
        return reportRepository.revenueMonthInYearAndMovie(year, movieId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [year, theaterId]
     */
    @Override
    public List<RevenueDTO> revenueMonthInYearAndTheater(String year, String theaterId) {
        return reportRepository.revenueMonthInYearAndTheater(year, theaterId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:35 AM
     * @Return: java.util.List<fa.cineverse.dto.RevenueDTO>
     * @Params: [year, provinceId]
     */
    @Override
    public List<RevenueDTO> revenueMonthInYearAndProvince(String year, String provinceId) {
        return reportRepository.revenueMonthInYearAndProvince(year, provinceId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [year]
     */
    @Override
    public List<TicketSoldDTO> ticketMonthInYear(String year) {
        return reportRepository.ticketMonthInYear(year);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [year, movieId]
     */
    @Override
    public List<TicketSoldDTO> ticketMonthInYearAndMovie(String year, String movieId) {
        return reportRepository.ticketMonthInYearAndMovie(year, movieId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [year, theaterId]
     */
    @Override
    public List<TicketSoldDTO> ticketMonthInYearAndTheater(String year, String theaterId) {
        return reportRepository.ticketMonthInYearAndTheater(year, theaterId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [year, provinceId]
     */
    @Override
    public List<TicketSoldDTO> ticketMonthInYearAndProvince(String year, String provinceId) {
        return reportRepository.ticketMonthInYearAndProvince(year, provinceId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [startDate, endDate]
     */
    @Override
    public List<TicketSoldDTO> ticketByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepository.ticketByPeriodTime(startDate, endDate);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [startDate, endDate, movieId]
     */
    @Override
    public List<TicketSoldDTO> ticketByPeriodTimeAndMovie(LocalDate startDate, LocalDate endDate, String movieId) {
        return reportRepository.ticketByPeriodTimeAndMovie(startDate, endDate, movieId);
    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:36 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [startDate, endDate, theater]
     */
    @Override
    public List<TicketSoldDTO> ticketByPeriodTimeAndTheater(LocalDate startDate, LocalDate endDate, String theater) {
        return reportRepository.ticketByPeriodTimeAndTheater(startDate, endDate, theater);

    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:37 AM
     * @Return: java.util.List<fa.cineverse.dto.TicketSoldDTO>
     * @Params: [startDate, endDate, province]
     */
    @Override
    public List<TicketSoldDTO> ticketByPeriodTimeAndProvince(LocalDate startDate, LocalDate endDate, String province) {
        return reportRepository.ticketByPeriodTimeAndProvince(startDate, endDate, province);

    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:37 AM
     * @Return: java.util.List<fa.cineverse.dto.Top3MovieInMonth>
     * @Params: [month]
     */
    @Override
    public List<Top3MovieInMonth> top3MovieInMonth(LocalDate month) {
        return reportRepository.top3MovieInMonth(month);
    }

    @Override
    public List<Top5TheaterInMonth> top5TheaterInMonth(LocalDate month) {
        return reportRepository.top5TheaterInMonth(month);
    }
}
