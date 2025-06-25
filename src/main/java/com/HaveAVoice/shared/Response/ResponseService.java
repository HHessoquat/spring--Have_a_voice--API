package com.HaveAVoice.shared.Response;

import org.springframework.stereotype.Service;

@Service
public class ResponseService<T> {
    public String code;
    public String message;
    public T body;

    public ResponseService() {}
    public ResponseService(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public static <T> ResponseService<T> build(String code, String message, T body) {
        return new ResponseService<T>(code, message, body);
    }
    public static ResponseService<Void> build(String code, String message) {
        return new ResponseService<Void>(code, message, null);
    }
 }
