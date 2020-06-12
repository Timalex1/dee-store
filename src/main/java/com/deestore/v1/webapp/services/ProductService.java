package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.domains.Category;
import com.deestore.v1.webapp.domains.Product;
import org.hibernate.SessionFactory;

import java.util.List;

public interface ProductService extends CRUDService<Product> {

    ProductForm saveOrUpdateProductForm(ProductForm productForm);
    String getAProductName(Long id);

    List<Product> latest();
    List<Product> featuredProducts();

    boolean hasCategory(Product product, Category category);
    void addCategory(Product product, Category category);
    void removeCategory(Product product, Category category);

    boolean hasProductsAssociated(Category category);

}
