package pl.sdacademy.booking.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
//brak byl buildera
@Data
@Builder
public class NewEventDto {

    private String itemName;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
