package com.fpes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRes {
    private String username;
    private String name;
    private String dateOfBirth;
    private String email;
}
