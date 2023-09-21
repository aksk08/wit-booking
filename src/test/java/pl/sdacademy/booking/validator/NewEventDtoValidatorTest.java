package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromIsNull() {
        NewEventDto input= NewEventDto.builder().itemName("przyklad")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023,9,19,19,56))
                .build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("From is null");
    }
    @Test
    void shouldCheckThatToIsNull() {
        NewEventDto input= NewEventDto.builder().itemName("przyklad")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023,9,19,19,56))
                .build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("To is null");
    }
    @Test
    void shouldCheckThatToAndFromIsNull() {
        NewEventDto input= NewEventDto.builder().itemName("przyklad")
                .toTime(null)
                .fromTime(null)
                .build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(2).contains("To is null");
    }
    @Test
    void shouldCheckToIsBeforeFrom(){
        NewEventDto input=NewEventDto.builder().itemName("przyklad")
                .fromTime(LocalDateTime.of(2023,9,19,15,56))
                .toTime(LocalDateTime.of(2023,9,19,15,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("To is before from");
    }
    @Test
    void shouldCheckEventIsTooLong(){
        NewEventDto input=NewEventDto.builder().itemName("przyklad")
                .fromTime(LocalDateTime.of(2023,9,19,15,6))
                .toTime(LocalDateTime.of(2023,9,19,15,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("Too long event");
    }
    @Test
    void shouldCheckEventIsTooLongAndNullItem(){
        NewEventDto input=NewEventDto.builder().itemName(null)
                .fromTime(LocalDateTime.of(2023,9,19,15,6))
                .toTime(LocalDateTime.of(2023,9,19,15,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(2).contains("Too long event","Without item");
    }
    @Test
    void shouldIsToIsBeforeFromAndItemIsNull(){
        NewEventDto input=NewEventDto.builder().itemName(null)
                .fromTime(LocalDateTime.of(2023,9,19,15,6))
                .toTime(LocalDateTime.of(2023,9,19,7,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(2).contains("To is before from","Without item");
    }
    @Test
    void shouldIsTooEarlyAndItemIsNull(){
        NewEventDto input=NewEventDto.builder().itemName(null)
                .fromTime(LocalDateTime.of(2023,9,19,7,15))
                .toTime(LocalDateTime.of(2023,9,19,7,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(3).contains("Too early or too late","Too long event","Without item");
    }
    @Test
    void shouldIsTooLongAndTooEarlyAndTooLate(){
        NewEventDto input=NewEventDto.builder().itemName(null)
                .fromTime(LocalDateTime.of(2023,9,19,7,15))
                .toTime(LocalDateTime.of(2023,9,20,16,55)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(3).contains("Too early or too late","Too long event","Without item");
    }
    @Test
    void shouldEverythingIsNull(){
        NewEventDto input=NewEventDto.builder().itemName(null)
                .fromTime(null)
                .toTime(null).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(3).contains("Without item","To is null", "From is null");
    }
    @Test
    void shouldAddCorrectly(){
        NewEventDto input=NewEventDto.builder().itemName("przykład")
                .fromTime(LocalDateTime.of(2023,9,19,8,15))
                .toTime(LocalDateTime.of(2023,9,19,8,25)).build();
        List<String> result=NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(0);
    }

    //actually date?

}