package com.pro.woo.dtos;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Data //tostring
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;

}
