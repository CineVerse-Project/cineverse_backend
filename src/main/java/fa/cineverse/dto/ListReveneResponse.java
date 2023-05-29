package fa.cineverse.dto;

import java.util.List;

/**
 * @Author AnP1
 * @Date: 12.05.2023
 */
public class ListReveneResponse {
    private List<RevenueDTO> currentPeriod;
    private List<RevenueDTO> previousPeriod;

    public ListReveneResponse() {
    }

    public ListReveneResponse(List<RevenueDTO> currentPeriod, List<RevenueDTO> previousPeriod) {
        this.currentPeriod = currentPeriod;
        this.previousPeriod = previousPeriod;
    }

    public List<RevenueDTO> getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(List<RevenueDTO> currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public List<RevenueDTO> getPreviousPeriod() {
        return previousPeriod;
    }

    public void setPreviousPeriod(List<RevenueDTO> previousPeriod) {
        this.previousPeriod = previousPeriod;
    }
}
