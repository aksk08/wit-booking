package pl.sdacademy.booking.mapper;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.data.ItemAttributeEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.ItemDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemDtoMapperTest {

    @Test
    void shouldMapEmptyEntityToDto() {
        ItemEntity input = new ItemEntity();
        Set<String> attributes = Set.of();

        ItemDto result = ItemDtoMapper.map(input);

//        assertThat(result).isNotNull()
//                .hasFieldOrPropertyWithValue("id", 0)
//                .hasFieldOrPropertyWithValue("name", null);
        assertThat(result).isNotNull()
                .isEqualTo(ItemDto.builder()
                        .id(0)
                        .price(null)
                        .description(null)
                        .name(null)
                        .attributes(Set.of())
                        .build()
                );
    }

    @Test
    void shouldMapNotEmptyEntityToDto() {
        ItemEntity input = new ItemEntity();
        input.setPrice(BigDecimal.TEN);
        input.setName("itemName");
        input.setId(100L);
        input.setDescription("itemDesc");
        //tworzenie atrybutów:
        ItemAttributeEntity attribute1 = new ItemAttributeEntity();
        attribute1.setId(1);
        attribute1.setAttributeName("1");
        ItemAttributeEntity attribute2 = new ItemAttributeEntity();
        attribute2.setId(2);
        attribute2.setAttributeName("2");

        Set<ItemAttributeEntity> attributes = Set.of(attribute1, attribute2);
        // dodawanie atrybutów do inputu:
        input.setAttributes(attributes);

        ItemDto result = ItemDtoMapper.map(input);

        assertThat(result).isNotNull()
                .isEqualTo(ItemDto.builder()
                        .id(100L)
                        .price(BigDecimal.TEN)
                        .description("itemDesc")
                        .name("itemName")
                        .attributes(Set.of("1", "2"))
                        .build()
                );

    }

}

