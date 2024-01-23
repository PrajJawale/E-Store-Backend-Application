package com.example.eStore.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> items;

    String customerName;
}
