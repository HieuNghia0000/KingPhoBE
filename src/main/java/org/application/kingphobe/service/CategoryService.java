package org.application.kingphobe.service;

import org.application.kingphobe.dto.CategoryDTO;
import org.application.kingphobe.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();

    Optional<Category> getCategoryById(int id);

    Category createCategory(CategoryDTO categoryDTO);

    Optional<Category> updateCategory(Integer id, CategoryDTO categoryDTO);

    void deleteCategory(int id);
}
