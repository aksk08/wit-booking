package pl.sdacademy.booking.model;

import lombok.*;

import java.math.BigDecimal;
@Data
public class NewItemDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
