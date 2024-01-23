package com.example.eStore.dto.requestDto;

import com.example.eStore.dto.responseDto.ItemResponseDto;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartReuestDto {

    Integer customerID;
    Integer cartTotal;
    Integer numberOfItems;
    List<ItemResponseDto> items;
}
