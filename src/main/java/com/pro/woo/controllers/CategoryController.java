package com.pro.woo.controllers;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.models.Category;
import com.pro.woo.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
//@Validated
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam("page") int page,
                                        @RequestParam("size") int size){
        List<Category> categories=categoryService.getAllCategories();
        //return ResponseEntity.ok(String.format("get all categories, page=%d, size=%d", page, size));
        return ResponseEntity.ok(categories);
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody CategoryDTO categoryDTO,
                                    BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("insert"+categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok("edit"+id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete"+id);
    }
}
