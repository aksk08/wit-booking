package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromTimeIsNull() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result)
                .hasSize(1)
                .contains("FromTime is null");
    }

    @Test
    void shouldCheckThatToTimeIsNull() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .toTime(null)
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result)
                .hasSize(1)
                .contains("ToTime is null");
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
    void shouldCheckThatNameIsNotNull(){
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(LocalDateTime.of(2023, 9, 21, 8, 45))
                .toTime(LocalDateTime.of(2023, 9, 21, 9, 0))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("Name is not set");
    }
    @Test
    void shouldCheckThatAllFieldsAreNull(){
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                .fromTime(null)
                .toTime(null)
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).containsExactly("Name is not set", "FromTime is null", "ToTime is null");
    }

    @Test
    void shouldCheckIfToTimeisAfterFromTime() {
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("test")
                .fromTime(LocalDateTime.of(2023, 9, 21, 8, 45))
                .toTime(LocalDateTime.of(2023, 9, 21, 8, 40))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("ToTime is before FromTime");
    }
    @Test
    void shouldCheckThatSessionIsTooLong(){
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("test")
                .fromTime(LocalDateTime.of(2023, 9, 21, 8, 45))
                .toTime(LocalDateTime.of(2023, 9, 21, 9, 45))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("Session is too long");
    }

    @Test    //skorzystać z klasy Clock
    void shouldCheckThatSessionStartsAfterTimeNow(){
        //given
        Clock clock = Clock.fixed(Instant.parse("2023-09-21T08:05:00.00Z"), ZoneId.of("Europe/Berlin"));
        LocalDateTime.now(clock);

        NewEventDto input = NewEventDto.builder()
                .itemName("test")
                .fromTime(LocalDateTime.of(2023, 9, 21, 8, 30))
                .toTime(LocalDateTime.of(2023, 9, 21, 9, 0)).build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("FromTime is before now");
    }
    @Test
    void shouldCheckThatSessionStartsBeforeWorkingHours(){
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("test")
                .fromTime(LocalDateTime.of(2023, 9, 21, 7, 45))
                .toTime(LocalDateTime.of(2023, 9, 21, 8, 0))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("FromTime is before 8:00");
    }
    @Test
    void shouldCheckThatSessionFinishesAfterWorkingHours(){
        //given
        NewEventDto input = NewEventDto.builder()
                .itemName("test")
                .fromTime(LocalDateTime.of(2023, 9, 21, 15, 45))
                .toTime(LocalDateTime.of(2023, 9, 21, 16, 5))
                .build();
        //when
        List<String> result = NewEventDtoValidator.validate(input);
        //then
        assertThat(result).contains("ToTime is after 16:00");
    }

    //sprawdzic poprawne wprowadzenie wszystkich danych. sprawdzic czy rozmiar listy komunikatow = 0

}