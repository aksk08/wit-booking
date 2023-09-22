package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromIsNull(){
        NewEventDto input = NewEventDto.builder().itemName("przykład")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023,9, 19, 19, 59))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).containsExactly("From is null");
    }
    @Test
    void shouldCheckThatToIsNull(){
        NewEventDto input = NewEventDto.builder().itemName("przykład")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023,9, 19, 19, 56))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).containsExactly("To is null");
    }
    @Test
    void shouldCheckThatToAndFromIsNull(){
        NewEventDto input = NewEventDto.builder().itemName("przykład")
                .toTime(null)
                .fromTime(null)
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(2).contains("To is null", "From is null");
    }
}