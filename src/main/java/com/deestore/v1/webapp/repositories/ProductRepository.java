package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductById(Long id);
    void deleteProductById(Long id);

    String getProductById(Long id);
}
