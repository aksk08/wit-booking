package pl.sdacademy.booking.controller;

import pl.sdacademy.booking.repository.ItemRepositoryImpl;
import pl.sdacademy.booking.service.EventService;
import pl.sdacademy.booking.service.ItemService;

public class ItemController {

    private final ItemService itemService;
    private final EventService eventService;

    public ItemController(ItemService itemService, EventService eventService) {
        // nie najlepsze rozwiazanie - skrot by moc zaprezentowac pseudo controller
        this.itemService = itemService;
        this.eventService = eventService;
    }

    public void presentCatalog() {
        System.out.println("\n KATALOG ");
        itemService.findItems()
                .forEach(itemDto -> System.out.println(itemDto));
        System.out.println("\n KONIEC KATALOGU ");
    }
    public void presentEvents() {
        System.out.println("\n WYDARZENIA ");
        eventService.findAll()
                .forEach(eventDto -> System.out.println(eventDto));
        System.out.println("\n KONIEC WYDARZEN ");
    }
}
