package pl.sdacademy.booking.model;

import jakarta.persistence.Column;
import pl.sdacademy.booking.data.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDto {
    //Dane z ItemEntity:
    private String name;
    private BigDecimal price;

    //dane z EventEntity:
    private ItemEntity item;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
