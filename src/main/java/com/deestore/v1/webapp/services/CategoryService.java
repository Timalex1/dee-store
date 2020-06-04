package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.domains.Category;

import java.util.List;

public interface CategoryService extends CRUDService<Category>{
    List<Category> findParent();
}
