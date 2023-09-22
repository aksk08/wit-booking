package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.ItemRepository;
import pl.sdacademy.booking.validator.NewEventDtoValidator;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EventService {
    private final ItemRepository itemRepository;
    private final EventRepository eventRepository;

    public EventService(ItemRepository itemRepository, EventRepository eventRepository) {
        this.itemRepository = itemRepository;
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

    public String addEvent(NewEventDto newEvent) {
        Long eventByDate = eventRepository.findEventByDate(newEvent.getFromTime());
        if (eventByDate != null) {
            return "Sesja już istnieje.";
        }

        List<String> validate = NewEventDtoValidator.validate(newEvent);
        if (validate.size() != 0) {
            String message = String.join(", ", validate);
            return message;
        }
        EventEntity eventEntity = new EventEntity();
        Long itemByName = itemRepository.findItemByName(newEvent.getItemName()); //szukamy primary key
        if (itemByName == null || itemByName == -1L) {
            return "Nie znaleziono takiego obiektu";
        }

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemByName);

        eventEntity.setItem(itemEntity); //przekazujemy tylko primary key
        eventEntity.setFrom(newEvent.getFromTime());
        eventEntity.setTo(newEvent.getToTime());
        eventRepository.addEvent(eventEntity);
        return "Sesja została zapisana";
    }
}
