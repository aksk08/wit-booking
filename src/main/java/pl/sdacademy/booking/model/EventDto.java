package pl.sdacademy.booking.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.sdacademy.booking.data.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class EventDto {
    //Dane z ItemEntity:
    private String name;
    private BigDecimal price;

    //dane z EventEntity:
    //private ItemEntity item;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
