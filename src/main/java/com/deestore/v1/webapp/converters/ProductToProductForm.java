package com.deestore.v1.webapp.converters;

import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.domains.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by donaldsmallidge on 3/9/17.
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {

    @Override
    public ProductForm convert(Product product) {

        ProductForm productForm = new ProductForm();
        productForm.setName(product.getProductName());
        productForm.setId(product.getId());
        productForm.setManufacturer(product.getProductManufacturer());
        productForm.setUnit(product.getUnitStock());
        productForm.setDescription(product.getProductDescription());
        productForm.setPrice(product.getNewPrice());
        productForm.setImageUrl(product.getProductImage());

        return productForm;
    }
}
