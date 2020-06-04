package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.domains.Product;
import com.deestore.v1.webapp.services.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService ps;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testlistAll() {
        List<Product> products = (List<Product>) ps.listAll();

        assertEquals(0, products.size());
    }
}