package com.pro.woo.controllers;


import ch.qos.logback.core.util.StringUtil;
import com.pro.woo.models.Category;
import com.pro.woo.models.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute Product product,

                                           BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }
            List<MultipartFile> files=product.getFiles();
            files=files==null?new ArrayList<MultipartFile>():files;
            //Kiêm tra kích thước file và định dạng
            for(MultipartFile file:files){
                if (file != null) {
                    if(file.getSize()==0){
                        continue;
                    }
                    if (file.getSize() > 10 * 1024 * 1024) {
                        return ResponseEntity.badRequest().body("File is too big");
                    }
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                .body("File must be an image");
                    }
                    String fileName=storeFile(file);
                }
            }
            return ResponseEntity.ok("insert" + product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
   private String storeFile(MultipartFile file) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFiName= UUID.randomUUID().toString()+"_"+fileName;
        java.nio.file.Path uploadDdir= Paths.get("upload");
        if(!Files.exists(uploadDdir)) {
            Files.createDirectory(uploadDdir);
        }
        java.nio.file.Path destination=Paths.get(uploadDdir.toString(), uniqueFiName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFiName;
   }
}
//{
//        "name":"Tai nghe 1",
//        "price":20000,
//        "thumbnail":"",
//        "description":"Không trả góp",
//        "category_id":1
//        }