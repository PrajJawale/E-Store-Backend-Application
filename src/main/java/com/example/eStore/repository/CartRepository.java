package com.example.eStore.repository;

import com.example.eStore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart,Integer> {
}
