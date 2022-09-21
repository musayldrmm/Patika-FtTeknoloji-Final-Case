package com.ftTeknoloji.finalcase.excetions;

import org.springframework.security.authentication.BadCredentialsException;

public class LoginErrorMessage extends BadCredentialsException {

    public LoginErrorMessage(String msg) {
        super(msg);
    }

    public LoginErrorMessage(String msg, Throwable cause) {
        super(msg, cause);
    }
}
