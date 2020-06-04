package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerById(Long id);
    void deleteCustomerById(Long id);
}
