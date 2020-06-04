package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.domains.Product;

public interface ProductService extends CRUDService<Product> {

    ProductForm saveOrUpdateProductForm(ProductForm productForm);
    String getAProductName(Long id);
}
