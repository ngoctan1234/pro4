package com.pro.woo.controllers;

import com.pro.woo.dtos.OrderDTO;
import com.pro.woo.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    @GetMapping("")
    public ResponseEntity<String> getAllOrderDetail(@RequestParam("page") int page,
                                               @RequestParam("size") int size){
        // List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.ok(String.format("get all order detail page=%d, size=%d", page, size));
        // return ResponseEntity.ok(categories);
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult result){
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
                                         @Valid @RequestBody OrderDetailDTO orderDetailDTO){
        // categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok("edit");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        //categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete"+id);
    }
}
