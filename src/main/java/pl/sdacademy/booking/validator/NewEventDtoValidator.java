package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {

    public static List<String> validate(NewEventDto newEventDto) {
        List<String> listOfMessages = new ArrayList<>();

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
            if (durationOfEvent.toMinutes() > 30) {
                String message = "Zabieg zbyt długi";
                listOfMessages.add(message);
            }
        }
        //todo Zad dom - dokończyć walidator, extra zrobić mapper w nowej paczce
        //czy data jest w przeszlosci //now
        //czy przypada na godziny pracy (8:00-16:00)
        //czy itemName nie jest nullem lub empty
        //długość trwania sesji
        return listOfMessages;
    }
}
