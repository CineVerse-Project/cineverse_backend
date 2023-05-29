package fa.cineverse.dto;

import java.util.List;

/**
 * @Author AnP1
 * @Date: 12.05.2023
 */
public class ListTicketResponse {
    private List<TicketSoldDTO> currentPeriod;
    private List<TicketSoldDTO> previousPeriod;

    public ListTicketResponse() {
    }

    public ListTicketResponse(List<TicketSoldDTO> currentPeriod, List<TicketSoldDTO> previousPeriod) {
        this.currentPeriod = currentPeriod;
        this.previousPeriod = previousPeriod;
    }

    public List<TicketSoldDTO> getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(List<TicketSoldDTO> currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public List<TicketSoldDTO> getPreviousPeriod() {
        return previousPeriod;
    }

    public void setPreviousPeriod(List<TicketSoldDTO> previousPeriod) {
        this.previousPeriod = previousPeriod;
    }
}
