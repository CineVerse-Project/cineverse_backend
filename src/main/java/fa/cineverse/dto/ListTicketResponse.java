package fa.cineverse.dto;

import java.util.List;

/**
 * @Author AnP1
 * @Date: 12.05.2023
 */
public class ListTicketResponse {
    private List<TicketDTO> currentPeriod;
    private List<TicketDTO> previousPeriod;

    public ListTicketResponse() {
    }

    public ListTicketResponse(List<TicketDTO> currentPeriod, List<TicketDTO> previousPeriod) {
        this.currentPeriod = currentPeriod;
        this.previousPeriod = previousPeriod;
    }

    public List<TicketDTO> getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(List<TicketDTO> currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public List<TicketDTO> getPreviousPeriod() {
        return previousPeriod;
    }

    public void setPreviousPeriod(List<TicketDTO> previousPeriod) {
        this.previousPeriod = previousPeriod;
    }
}
