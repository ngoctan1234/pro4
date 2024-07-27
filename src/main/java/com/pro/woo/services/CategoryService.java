package com.pro.woo.services;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.models.Category;
import com.pro.woo.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService  implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory =  Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return  categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
       Category existingCategory = getCategoryById(categoryId);
       existingCategory.setName(categoryDTO.getName());
       categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        return  categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
         categoryRepository.deleteById(id);
    }
}
