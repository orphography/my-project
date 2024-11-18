package ru.roh.springdemo.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.roh.springdemo.models.User;

@Data
public class UserDTO {
    @NotEmpty(message = "Firstname should not be empty")
    @Size(min = 2, max = 30, message = "Firstname should be between 2 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname should be between 2 and 30 characters")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email must have the format of an email address")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 30 characters")
    // TODO @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private User.Role role; // например, "MANAGER" или "USER"
}
