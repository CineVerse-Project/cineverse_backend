package fa.cineverse.dto;

import java.time.LocalDateTime;

/**
 * @Author AnP1
 * @Date: 12.05.2023
 */
public interface ScheduleCheckDTO {
    String getRoomId();
    LocalDateTime getScheduleDateTime();
}
