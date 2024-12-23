package com.example.userserviceapidesign.models.dto;

import com.example.userserviceapidesign.validation.CreateUser;
import com.example.userserviceapidesign.validation.EmailOrNumber;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EmailOrNumber
public class UserDTO {

    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "User ID should only contain letters, digits, and hyphens")
    private String userId;

    @Email(message = "Email should be in a valid format")
    private String email;

    @Pattern(regexp = "^[0-9]*$", message = "Number should only contain digits and can be empty")
    private String number;

    @NotBlank(message = "Name must not be blank",groups = CreateUser.class)
    private String name;

    @NotBlank(message = "Surname must not be blank",groups = CreateUser.class)
    private String surname;

    @NotNull(message = "Date of birth must not be null",groups = CreateUser.class)
    @Past(message = "Date of birth must be in the past")

    private LocalDate dateOfBirth;
    @NotBlank(message = "Gender must not be blank",groups = CreateUser.class)
    private String gender;

    @NotBlank(message = "Country must not be blank",groups = CreateUser.class)
    private String country;

    @NotBlank(message = "City must not be blank",groups = CreateUser.class)
    private String city;
    private String profileImageUrl;
    private String coverImageUrl;    

}
