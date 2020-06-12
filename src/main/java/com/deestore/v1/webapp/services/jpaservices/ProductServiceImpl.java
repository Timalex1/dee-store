package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.converters.ProductFormToProduct;
import com.deestore.v1.webapp.converters.ProductToProductForm;
import com.deestore.v1.webapp.domains.Category;
import com.deestore.v1.webapp.domains.Product;
import com.deestore.v1.webapp.repositories.ProductRepository;
import com.deestore.v1.webapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFormToProduct productFormToProduct;
    @Autowired
    private ProductToProductForm productToProductForm;


    @PersistenceContext
    private EntityManager entityManager;;

    @Override
    public ProductForm saveOrUpdateProductForm(ProductForm productForm) {
        // instructor version
        if (productForm.getId() != null) { // existing product
            Product productToUpdate = this.getById(productForm.getId());
            productToUpdate.setProductName(productForm.getName());
            productToUpdate.setProductDescription(productForm.getDescription());
            productToUpdate.setProductPrice(productToUpdate.getNewPrice());
            productToUpdate.setNewPrice(productForm.getPrice());
            productToUpdate.setProductManufacturer(productForm.getManufacturer());
            productToUpdate.setUnitStock(productForm.getUnit());
//            productToUpdate.addProductToCategory(productForm.getCategory());
            productToUpdate.setProductImage(productForm.getImageUrl());

            return productToProductForm.convert(this.saveOrUpdate(productToUpdate));

        } else { // new product
            return productToProductForm.convert(this.saveOrUpdate(productFormToProduct.convert(productForm)));
        }

    }

    @Override
    public String getAProductName(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> latest() {
        return productRepository.findTop6ByOrderById();
    }

    @Override
    public List<Product> featuredProducts() {
        return productRepository.findTop3ByFeatureIsTrue();
    }

    @Override
    public boolean hasCategory(Product product, Category category) {
        return product.getFallIntoCategories().contains(category);
    }

    @Override
    public void addCategory(Product product, Category category) {
        product.getFallIntoCategories().add(category);
        productRepository.save(product);
    }

    @Override
    public void removeCategory(Product product, Category category) {
        product.getFallIntoCategories().remove(category);
        productRepository.save(product);
    }

    @Override
    public boolean hasProductsAssociated(Category category) {
//        return productRepository.countByAssociatedWithOrFallIntoCategories(category.getId()) > 0;
    return false;
    }

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); // fun with Java 8
        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        if(domainObject.getNewPrice() != 0){
            return productRepository.save(domainObject);
        }
        else
            domainObject.setNewPrice(domainObject.getProductPrice());
            return productRepository.save(domainObject);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.deleteProductById(id);
    }
}
