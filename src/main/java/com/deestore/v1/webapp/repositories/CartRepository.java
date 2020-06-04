package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Cart;
import com.deestore.v1.webapp.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
