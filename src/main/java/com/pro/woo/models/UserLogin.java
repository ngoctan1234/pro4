package com.pro.woo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotBlank(message = "Phone number cannot be blank")
    @JsonProperty("phone_number")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
