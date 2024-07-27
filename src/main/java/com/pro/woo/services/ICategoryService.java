package com.pro.woo.services;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(Long categoryId);
    Category updateCategory(Long categoryId, CategoryDTO category);
    List<Category> getAllCategories();
    void deleteCategory(Long id);

}

