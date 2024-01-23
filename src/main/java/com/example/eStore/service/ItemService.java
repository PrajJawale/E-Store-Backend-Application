package com.example.eStore.service;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.ItemRequestDto;
import com.example.eStore.exception.InvalidProductException;
import com.example.eStore.model.Item;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
