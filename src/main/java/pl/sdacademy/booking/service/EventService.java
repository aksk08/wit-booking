package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.ItemRepository;
import pl.sdacademy.booking.validator.NewEventDtoValidator;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EventService {

    private final EventRepository eventRepository;
    private final ItemRepository itemRepository;

    public EventService(EventRepository eventRepository, ItemRepository itemRepository) {
        this.eventRepository = eventRepository;
        this.itemRepository = itemRepository;
    }

    public List<EventDto> findEvents() {
        log.info("findEvents");
        List<EventDto> result = new ArrayList<>();
        List<EventEntity> eventEntities = eventRepository.findEvents();

        for (EventEntity entity : eventEntities) {
            result.add(EventDto.builder()
                    .itemName(entity.getItem().getName())
                    .itemPrice(entity.getItem().getPrice())
                    .fromTime(entity.getFrom())
                    .toTime(entity.getTo())
                    .build());
        }
        return result;
    }

    public String addEvent(NewEventDto newEvent) {
        List<String> errors = NewEventDtoValidator.validate(newEvent, Clock.systemDefaultZone());
        if (!errors.isEmpty()) {
            String message = String.join(",", errors);
            return message;
        }
        Long eventsByName = eventRepository.findEventsByDate(newEvent.getFromTime());
        if (eventsByName != null) {
            return "Sesja już istnieje.";
        }
        EventEntity eventEntity = new EventEntity();
        // ponizsze rozwiazanie korzysta z cechy hibernate.
        // Nie jest to oczywiste i wielu reviewerow moze miec do tego uwagi
        // Bezpieczniej byloby zrobic findItemEntityByName w ItemRepository i zwrocic caly entity
        Long itemId = itemRepository.findItemByName(newEvent.getItemName());
        if (itemId == null || itemId.longValue() == -1) {
            return "Set item cannot be found, so event was not stored";
        }
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemId);
        //tutaj bedzie wyszukiwanie id_itemu po jego nazwie - być może można wykorzystać metode repostitory Item findbyName
        //eventEntity.setItem(itemId)
        eventEntity.setFrom(newEvent.getFromTime());
        eventEntity.setTo(newEvent.getToTime());
        eventRepository.addEvent(eventEntity);
        return "Sesja została zapisana";
    }


}