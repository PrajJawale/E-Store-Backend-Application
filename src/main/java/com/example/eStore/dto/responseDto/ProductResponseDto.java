package com.example.eStore.dto.responseDto;

import com.example.eStore.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    String productName;

    String sellerName;

    int quantity;

    ProductStatus productStatus;

}
