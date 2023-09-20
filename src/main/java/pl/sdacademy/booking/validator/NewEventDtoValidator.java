package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {
    public static List<String> validate(NewEventDto newEventDto) {
        List<String> result = new ArrayList<>();
        //daty not null
        if(newEventDto.getFromTime()==null){
            result.add("From is null");
        }
        if(newEventDto.getToTime()==null){
            result.add("To is null");
        }
        if(newEventDto.getFromTime() != null && newEventDto.getToTime() != null){
        Duration duration  = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());

        //date in the future
        //date from 8 - 16
        // item name is null or empty
        //how long is event
        if (duration.isNegative()){
            result.add("To is before from");
        }
        if(duration.toMinutes()>30){
            result.add("To is before from");}}
        return result;
    }
}
