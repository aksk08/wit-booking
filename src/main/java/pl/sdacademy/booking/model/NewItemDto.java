package pl.sdacademy.booking.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Slf4j
@Data
public class NewItemDto {
    private String name;
    private String description;
    private BigDecimal price;
}
