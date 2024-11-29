package com.ahmetaltun.securedoc.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @NotBlank(message = "First name is required")
    @Length(min = 2, max = 32, message = "First name must be between {min} and {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Length(min = 2, max = 32, message = "Last name must be between {min} and {max} characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Length(max = 64, message = "Email cannot exceed {max} characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 16, message = "Password must be between {min} and {max} characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&+-])[A-Za-z\\d@$!%*?&+-]{8,16}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
    )
    private String password;

    @Length(max = 500, message = "Bio cannot exceed {max} characters")
    private String bio;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phone;
}
