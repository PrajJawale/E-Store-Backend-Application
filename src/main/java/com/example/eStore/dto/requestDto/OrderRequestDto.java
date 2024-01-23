package com.example.eStore.dto.requestDto;

import com.example.eStore.dto.responseDto.ItemResponseDto;
import com.example.eStore.model.Customer;
import com.example.eStore.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    int customerId;

    int productId;

    int requiredQuantity;

    String cardNo;

    int cvv;
}
