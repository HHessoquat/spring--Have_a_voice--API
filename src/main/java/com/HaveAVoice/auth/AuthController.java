package com.HaveAVoice.auth;

import com.HaveAVoice.auth.bll.AuthService;
import com.HaveAVoice.shared.Response.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public  ResponseEntity<ResponseService<AuthResponse>> login(@RequestBody Credentials credentials) {
        var res = authService.login(credentials);
        return ResponseEntity.ok(res);
    }
}
