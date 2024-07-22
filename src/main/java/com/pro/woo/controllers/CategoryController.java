package com.pro.woo.controllers;

import com.pro.woo.models.Category;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
//@Validated
public class CategoryController {
    @GetMapping("")
    public ResponseEntity<String> index(@RequestParam("page") int page,@RequestParam("size") int size){
        return ResponseEntity.ok(String.format("get all categories, page=%d, size=%d", page, size));
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody Category category, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok("insert"+category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id){
        return ResponseEntity.ok("edit"+id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok("delete"+id);
    }
}
