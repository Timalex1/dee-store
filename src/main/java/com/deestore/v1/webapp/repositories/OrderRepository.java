package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
    void deleteOrderById(Long id);
}
