package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);
    Set<Category> findByParentIsNull();

}
