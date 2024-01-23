package com.example.eStore.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {
    String name;

    String emailId;

    Integer age;

    String mobNo;
}
