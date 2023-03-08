package com.fpes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserReq {
    @NotNull
    @Min(value = 6, message = "Username required length is 6 characters.")
    private String username;
    @NotNull
    private String password;
    private String name;
    @NotNull
    private String email;
    private String dateOfBirth;
    private String role;
}
