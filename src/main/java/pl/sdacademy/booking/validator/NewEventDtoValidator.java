package pl.sdacademy.booking.validator;

import org.apache.commons.lang3.StringUtils;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {

    public static List<String> validate(NewEventDto newEventDto) {
        List<String> listOfMessages = new ArrayList<>();

        if (newEventDto == null) {
            String message = "Event is null";
            listOfMessages.add(message);
            return listOfMessages;
        }

//        if (newEventDto.getItemName() == null || newEventDto.getItemName().isEmpty()) {
        if (StringUtils.isBlank(newEventDto.getItemName())) {
            String message = "Name is not set";
            listOfMessages.add(message);
        }
        if (newEventDto.getFromTime() == null) {
            String message = "FromTime is null";
            listOfMessages.add(message);
        }
        if (newEventDto.getToTime() == null) {
            String message = "ToTime is null";
            listOfMessages.add(message);
        }

        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration durationOfEvent = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            if (durationOfEvent.isNegative()) {
                String message = "ToTime is before FromTime";
                listOfMessages.add(message);
            }
            if (durationOfEvent.toMinutes() >= 30) {
                String message = "Session is too long";
                listOfMessages.add(message);
            }
            //czy data jest w przeszlosci //now
            Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
            LocalDateTime timeNow = LocalDateTime.now(clock);
            if (newEventDto.getFromTime().isBefore(timeNow)) {
                String message = "FromTime is before now";
                listOfMessages.add(message);
            }
            //czy przypada na godziny pracy (8:00-16:00)
            if (newEventDto.getFromTime().getHour() < 8) {
                String message = "FromTime is before 8:00";
                listOfMessages.add(message);
            }
            if (newEventDto.getToTime().getHour() >= 16) {
                String message = "ToTime is after 16:00";
                listOfMessages.add(message);
            }
        }

        return listOfMessages;
    }
}
