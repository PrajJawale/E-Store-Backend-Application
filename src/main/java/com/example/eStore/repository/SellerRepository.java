package com.example.eStore.repository;

import com.example.eStore.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer>{
    Seller findByEmailId(String email);
}