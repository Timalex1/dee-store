package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.domains.Category;
import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.repositories.CategoryRepository;
import com.deestore.v1.webapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<?> listAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories :: add);
        return categories;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category saveOrUpdate(Category domainObject) {
        return categoryRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        Category cat = categoryRepository.findCategoryById(id);
        categoryRepository.delete(cat);
    }

    @Override
    public List<Category> findParent() {
        return null;
    }

    @Override
    public void removeChildCategory(Category category, Category parent) {
        category.setParent(null);
        categoryRepository.save(category);
    }

    @Override
    public void addChildCategory(Category category, Category parent) {
        category.setParent(parent);
        categoryRepository.save(category);
    }

    @Override
    public boolean isChildCategory(Category category, Category parent) {
        return category.getParent().equals(parent);
    }

}
