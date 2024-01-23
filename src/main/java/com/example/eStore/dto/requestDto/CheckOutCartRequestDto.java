package com.example.eStore.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutCartRequestDto {
    int customerID;
    String cardNo;
    int cvv ;
}
