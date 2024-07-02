package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.CategoryDTO;
import org.application.kingphobe.model.Category;
import org.application.kingphobe.repository.CategoryRepository;
import org.application.kingphobe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            existingCategory.get().setName(categoryDTO.getName());
            existingCategory.get().setDescription(categoryDTO.getDescription());
            return Optional.of(categoryRepository.save(existingCategory.get()));
        }

        return Optional.empty();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
