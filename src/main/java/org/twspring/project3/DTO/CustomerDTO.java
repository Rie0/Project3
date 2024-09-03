package org.twspring.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    //has user and customer info
    //create a user object in the register

    @NotEmpty(message = "Username cannot be empty")
    @Size(min=4,max = 10,
            message = "Username must have between 4 to 10 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{6,}$",
            message = "Password must be strong (at least: at least 6 characters, one uppercase letter, one lowercase letter, one number, and one special character)")
    private String password;
    //USER INFO VARS
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    @Size(min=4,max = 10,
            message = "Name must have between 2 to 20 letters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must have a valid format")
    @Size(min=7,max = 50,
            message = "Email must have between 7 to 50 letters")
    private String email;
    //CUSTOMER VARS
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$",
            message = "Phone number must start with 05 and be followed by 8 digits")
    private String phoneNumber;

}
