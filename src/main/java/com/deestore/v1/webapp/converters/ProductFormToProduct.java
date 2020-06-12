package com.deestore.v1.webapp.converters;

import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.domains.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by donaldsmallidge on 3/9/17.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    public Product convert(ProductForm productForm) {

        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        String ld = LocalDate.now().format(dtf);

        Product product = new Product();
        product.setId(productForm.getId());
        product.setProductName(productForm.getName());
        product.setProductManufacturer(productForm.getManufacturer());
        product.setDateCreated(ld);
//        product.addProductToCategory(productForm.getCategory());
        product.setProductDescription(productForm.getDescription());
        product.setProductPrice(product.getNewPrice());
        product.setNewPrice(productForm.getPrice());
        product.setProductImage(productForm.getImageUrl());
        product.setUnitStock((productForm.getUnit()));

        return product;
    }
}
