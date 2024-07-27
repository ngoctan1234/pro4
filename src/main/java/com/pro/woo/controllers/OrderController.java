package com.pro.woo.controllers;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.dtos.OrderDTO;
import com.pro.woo.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping("")
    public ResponseEntity<String> getAllOrders(@RequestParam("page") int page,
                                                           @RequestParam("size") int size){
       // List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.ok(String.format("get all orders, page=%d, size=%d", page, size));
       // return ResponseEntity.ok(categories);
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody OrderDTO orderDTO, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }
       // categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("insert");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody OrderDTO orderDTO){
       // categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok("edit");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        //categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete"+id);
    }
}
