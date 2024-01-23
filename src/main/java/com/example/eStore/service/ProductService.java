package com.example.eStore.service;

import com.example.eStore.Enum.ProductCategory;
import com.example.eStore.dto.requestDto.ProductRequestDto;
import com.example.eStore.dto.responseDto.ProductResponseDto;
import com.example.eStore.exception.InvalidSelllerException;
import com.example.eStore.model.Item;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSelllerException;
    List<ProductResponseDto> getAllProductsByCategory(ProductCategory productCategory);

    public void decreaseProductQuantity(Item item) throws Exception;
}
