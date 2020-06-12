package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.domains.Category;

import java.util.List;

public interface CategoryService extends CRUDService<Category>{
    List<Category> findParent();
    void removeChildCategory(Category category, Category parent);
    void addChildCategory(Category category, Category parent);
    boolean isChildCategory(Category category, Category parent);
}
