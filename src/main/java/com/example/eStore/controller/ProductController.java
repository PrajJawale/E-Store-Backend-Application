package com.example.eStore.controller;

import com.example.eStore.Enum.ProductCategory;
import com.example.eStore.dto.requestDto.ProductRequestDto;
import com.example.eStore.dto.responseDto.ProductResponseDto;
import com.example.eStore.exception.InvalidSelllerException;
import com.example.eStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSelllerException {

        return productService.addProduct(productRequestDto);
    }

    @GetMapping("/getproduct/{category}")
    public List<ProductResponseDto> getProductBYCategory(@PathVariable("category") ProductCategory category) {
        return productService.getAllProductsByCategory(category);
    }
}