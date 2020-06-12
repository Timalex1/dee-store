package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductById(Long id);
    void deleteProductById(Long id);

    String getProductById(Long id);

    @Query(value = "select p from product p order by p.id desc LIMIT 4", nativeQuery = true)
    List<Product> latestFour(@Param("id") Long id);

    List<Product> findTop6ByOrderById();

    List<Product> findTop3ByFeatureIsTrue();

}
