package com.example.eStore.dto.responseDto;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {

    String name;
    String message;
}
