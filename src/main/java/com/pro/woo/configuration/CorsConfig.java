package com.pro.woo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
              //  registry.addMapping("/**");

                registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                        .allowedOrigins("http://localhost:3000") // Cho phép yêu cầu từ nguồn gốc cụ thể
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức cụ thể
                        .allowedHeaders("Authorization", "Content-Type", "Header-Name") // Các header được phép
                        .allowCredentials(true); // Cho phép gửi cookie và thông tin xác thực
            }
        };
    }
}