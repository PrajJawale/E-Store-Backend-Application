package com.example.eStore.dto.requestDto;

import com.example.eStore.Enum.ProductCategory;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    int sellerId;

    String productName;

    int price;

    int quantity;

    ProductCategory productCategory;
}
