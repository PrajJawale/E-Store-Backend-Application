package com.example.eStore.transeformer;

import com.example.eStore.Enum.ProductStatus;
import com.example.eStore.dto.requestDto.ProductRequestDto;
import com.example.eStore.dto.responseDto.ProductResponseDto;
import com.example.eStore.model.Product;
import com.example.eStore.repository.ProductRepository;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
