package com.example.userserviceapidesign.validation;

import com.example.userserviceapidesign.models.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailOrNumberValidator implements ConstraintValidator<EmailOrNumber, UserDTO> {

    @Override
    public void initialize(EmailOrNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        if ((userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) &&
                (userDTO.getNumber() == null || userDTO.getNumber().isEmpty())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Either email or number must be provided")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
