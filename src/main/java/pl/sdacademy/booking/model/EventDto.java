package pl.sdacademy.booking.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@EqualsAndHashCode

public class EventDto {
    private String name; //lepiej eventName
    private BigDecimal price; //lepiej eventPrice
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
