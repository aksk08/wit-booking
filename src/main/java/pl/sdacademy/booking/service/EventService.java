package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Slf4j
public class EventService {

    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findAll(){
        log.info("findEvents");
        List<EventDto> result = new ArrayList<>();

        List<EventEntity> eventEntities = eventRepository.findAll();
        for(EventEntity event: eventEntities){
            result.add(EventDto.builder()
//                    .item(event.getItem())
                    .name(event.getItem().getName())
                    .price(event.getItem().getPrice())
                    .from(event.getFrom())
                    .to(event.getTo())
                    .build());
        }
        return result;
    }

}
