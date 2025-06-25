package com.HaveAVoice.shared.Exception;

import lombok.Getter;

@Getter
public class InvalidCredentials extends RuntimeException {
    private final String code;
    public InvalidCredentials(String code) {
        super("Invalid credentials Error");
        this.code = code;
    }
}
