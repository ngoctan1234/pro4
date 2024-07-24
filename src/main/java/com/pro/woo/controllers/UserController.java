package com.pro.woo.controllers;

import com.pro.woo.models.User;
import com.pro.woo.models.UserLogin;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody User user,
            BindingResult result
            ) {
        try{
            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }
            if(!user.getPassword().equals(user.getRetypePassword())){
                return ResponseEntity.badRequest().body("Passwords do not match");
            }
            return ResponseEntity.ok("Register successfull");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin user) {
        try{
            return ResponseEntity.ok().body("Login successfully, some token");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/login")
    public ResponseEntity<String> loginDelete() {
        try{
            return ResponseEntity.ok().body("delete successfully, some token");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
