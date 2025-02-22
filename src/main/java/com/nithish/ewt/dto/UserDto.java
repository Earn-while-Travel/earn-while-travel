package com.nithish.ewt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "User name cannot be null")
    private String userName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String userGmail;

    @NotNull(message = "Register number cannot be null")
    private String userRegisterNbr;


}
