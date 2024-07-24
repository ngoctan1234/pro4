package com.pro.woo.models;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Data //tostring
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;

    public static class UserLoginModel {
    }
}
