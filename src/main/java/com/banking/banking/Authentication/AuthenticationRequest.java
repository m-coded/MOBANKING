package com.banking.banking.Authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
