package pl.sdacademy.booking.mapper;

import pl.sdacademy.booking.data.ItemAttributeEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.ItemDto;

import java.util.HashSet;
import java.util.Set;

public class ItemDtoMapper { //zmieniają postać obiektów

    public static ItemDto map(ItemEntity entity) {
        //konwersja ItemAttributeEntity na String:
        Set<ItemAttributeEntity> attributes = entity.getAttributes();
        Set<String> attributeNames = new HashSet<>();

        for (ItemAttributeEntity attributeEntity : attributes) {
            attributeNames.add(attributeEntity.getAttributeName());
        }

        //mapper:
        return ItemDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .attributes(attributeNames)
                .build();
    }
}
