package com.example.eStore.dto.requestDto;

import com.example.eStore.Enum.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String emailId;

    String cardNo;

    int cvv;

    Date expiryDate;


    CardType cardType;

     // To set the card of the particular customer
}
