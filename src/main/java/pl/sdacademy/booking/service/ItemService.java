package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.ItemAttributeEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.mapper.ItemDtoMapper;
import pl.sdacademy.booking.model.ItemDto;
import pl.sdacademy.booking.model.NewItemDto;
import pl.sdacademy.booking.repository.ItemRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findItems() {
        log.info("findItems");
        List<ItemDto> result = new ArrayList<>();

        List<ItemEntity> itemEntities = itemRepository.findItems();
        for (ItemEntity entity : itemEntities) {
            result.add(ItemDtoMapper.map(entity));  //mapuje dane
        }
        return result;
    }

    public String addItem(NewItemDto newItem){
        Long itemByName = itemRepository.findItemByName(newItem.getName());
        if(itemByName != null){
            return "Element istnieje";
        }
        ItemEntity itemEntity = new ItemEntity();
                itemEntity.setName(newItem.getName());
                itemEntity.setDescription(newItem.getDescription());
                itemEntity.setPrice(newItem.getPrice());
                itemRepository.addItem(itemEntity);
        return "Item zapisany";
    }
}
