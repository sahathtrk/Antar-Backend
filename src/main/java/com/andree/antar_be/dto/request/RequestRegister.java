package com.andree.antar_be.dto.request;

import com.andree.antar_be.models.User;
import com.andree.antar_be.utils.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegister {

    @NotEmpty(message = "email is required")
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    String email;
    @NotEmpty(message = "password is required")
    @Size(min = 8, message = "password must more than 8 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "password must contains lower case, upper case, and contains special character")
    String password;

    @NotEmpty(message = "full name is required")
    @Size(min = 8, message = "password must more than 8 characters")
    String fullName;
    @NotEmpty(message = "phone number is required")
    @Size(min = 8,max = 16, message = "password must more than 8 characters and less than 16")
    String phoneNumber;

    @NotEmpty(message = "gender is required")
    @Pattern(regexp = "Male|Female",flags = Pattern.Flag.CASE_INSENSITIVE,message = "value must between male and female")
    @Size(min = 4,max = 7, message = "password must more than 8 characters")
    String gender;

    public User toUser(){
        return User.builder()
                .email(this.email)
                .fullName(this.fullName)
                .gender(this.gender.toLowerCase())
                .password(Password.createPassword(this.password))
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
