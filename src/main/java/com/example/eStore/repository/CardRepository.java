package com.example.eStore.repository;

import com.example.eStore.model.Card;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardNo(String cardNo);
}
