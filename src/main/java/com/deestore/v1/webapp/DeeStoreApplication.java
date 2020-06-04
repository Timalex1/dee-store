package com.deestore.v1.webapp;

import com.deestore.v1.webapp.domains.Product;
import com.deestore.v1.webapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DeeStoreApplication {

    @Autowired
    static ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(DeeStoreApplication.class, args);
    }

}
