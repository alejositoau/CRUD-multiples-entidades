package com.sena.database_connection.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    @NotNull
    @NotBlank
    private String name = "";

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(1)
    @Max(120)
    private int age;

    @Pattern(regexp = "\\d{10}")
    private String phone;
}