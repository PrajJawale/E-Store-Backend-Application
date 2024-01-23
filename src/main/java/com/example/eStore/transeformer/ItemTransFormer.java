package com.example.eStore.transeformer;

import com.example.eStore.dto.requestDto.ItemRequestDto;
import com.example.eStore.dto.responseDto.ItemResponseDto;
import com.example.eStore.model.Item;

public class ItemTransFormer {
    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
}
