package com.auth.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @NotEmpty(message = "Username must not be empty")
    private String username;
    @NotEmpty(message = "Email must not be empty")
    @Email(message="Email must be valid")
    private String email;
    @NotEmpty(message="Password must not be empty")
    private String password;
}
