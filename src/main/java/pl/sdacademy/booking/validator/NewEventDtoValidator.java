package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {

    public static List<String> validate(NewEventDto newEventDto) {
        List<String> listOfMessages = new ArrayList<>();

        if (newEventDto.getItemName() == null) {
            String message = "Name is null";
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

        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null && newEventDto.getItemName() != null) {
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
            LocalDateTime timeNow = LocalDateTime.now();
            if (newEventDto.getFromTime().isBefore(timeNow)) {
                String message = "FromTime is before now";
                listOfMessages.add(message);
            }
            //czy przypada na godziny pracy (8:00-16:00)
            if (newEventDto.getFromTime().getHour() < 8) {
                String message = "FromTime is before 8:00";
                listOfMessages.add(message);
            }
            if (newEventDto.getToTime().getHour() >= 16 && newEventDto.getToTime().getMinute() >= 0) {
                String message = "ToTime is after 16:00";
                listOfMessages.add(message);
            }
            if (newEventDto.getItemName().isEmpty()) {
                String message = "Name is empty";
                listOfMessages.add(message);
            }

        }
        //todo Zad dom - dokończyć walidator, extra zrobić mapper w nowej paczce

        return listOfMessages;
    }
}
