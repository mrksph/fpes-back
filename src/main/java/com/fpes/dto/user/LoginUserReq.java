package com.fpes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserReq {
    private String email;
    private String username;
    private String password;
}
