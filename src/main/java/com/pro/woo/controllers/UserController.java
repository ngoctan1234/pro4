package com.pro.woo.controllers;

import com.pro.woo.dtos.UserDTO;
import com.pro.woo.dtos.UserLoginDTO;
import com.pro.woo.services.IUserService;
import com.pro.woo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
            ) {
        try{
            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Passwords do not match");
            }
            userService.createUser(userDTO);
            return ResponseEntity.ok("Register successfull"+userDTO);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try{
            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
            // Trả về token trong response
            return ResponseEntity.ok(token);
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
