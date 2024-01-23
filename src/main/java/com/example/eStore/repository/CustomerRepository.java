package com.example.eStore.repository;

import com.example.eStore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);

    Customer findByEmailId(String emailId);
}
