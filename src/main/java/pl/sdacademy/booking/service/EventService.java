package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findAll() {
        log.info("findEvents");
        List<EventDto> result = new ArrayList<>();

        List<EventEntity> eventEntities = eventRepository.findAll();
        for (EventEntity event : eventEntities) {
            result.add(EventDto.builder()
                    .id(event.getId())
                    .itemName(event.getItem().getName())
                    .itemPrice(event.getItem().getPrice())
                    .fromTime(event.getFrom())
                    .toTime(event.getTo())
                    .build());
        }
        return result;
    }

//    public String addItem(NewEventDto newEvent){
//        Long eventByName = eventRepository
//    }
}
