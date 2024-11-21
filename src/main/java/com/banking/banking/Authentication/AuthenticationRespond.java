package com.banking.banking.Authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRespond {
    public AuthenticationRespond(String jwt) {
        this.jwt = jwt;
    }

    String jwt;
}
