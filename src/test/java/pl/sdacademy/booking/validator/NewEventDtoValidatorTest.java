package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromTimeIsNull() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023,9, 19, 19, 56))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result)
                .hasSize(1)
                .containsExactly("FromTime is null");
    }

    @Test
    void shouldCheckThatToTimeIsNull() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(LocalDateTime.of(2023,9, 19, 19, 56))
                .toTime(null)
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result)
                .hasSize(1)
                .containsExactly("ToTime is null");
    }

    @Test
    void shouldCheckThatFromTimeAndToTimeIsNull() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(null)
                .toTime(null)
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result)
                .hasSize(2)
                .containsExactly("FromTime is null", "ToTime is null");
    }


    @Test
    void shouldCheckThatNameIsEmptyFromTimeToTimeIsNull() {}
}