package com.pro.woo.models;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data //tostring
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;
}
