package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.time.LocalDateTime;
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
            //czy data jest w przeszlosci //now
            LocalDateTime timeNow = LocalDateTime.now();
            if (newEventDto.getFromTime().isBefore(timeNow)) {
                String message = "Wprowadzony termin rozpoczęcia już minął";
                listOfMessages.add(message);
            }
            //czy przypada na godziny pracy (8:00-16:00)
            if (newEventDto.getFromTime().getHour() < 8) {
                String message = "Wprowadzony termin rozpoczęcia sesji znajduje się przed otwarciem salonu";
                listOfMessages.add(message);
            }
            if (newEventDto.getToTime().getHour() > 16) {
                String message = "Wprowadzony termin zakończenia sesji znajduje się po zamknięciu salonu";
                listOfMessages.add(message);
            }
            //czy itemName nie jest nullem lub empty
            if (newEventDto.getItemName() == null) {
                String message = "Wprowadzona nazwa jest nullem";
                listOfMessages.add(message);
            }
            if (newEventDto.getItemName().isEmpty()) {
                String message = "Wprowadzona nazwa jest pusta";
                listOfMessages.add(message);
            }

        }
        //todo Zad dom - dokończyć walidator, extra zrobić mapper w nowej paczce

        return listOfMessages;
    }
}
