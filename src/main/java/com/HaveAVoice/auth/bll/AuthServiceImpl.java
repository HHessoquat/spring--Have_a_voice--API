package com.HaveAVoice.auth.bll;

import com.HaveAVoice.auth.AuthResponse;
import com.HaveAVoice.auth.Credentials;
import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.Exception.InvalidCredentials;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MessageHelper message;

    public AuthServiceImpl(JwtService js, AuthenticationManager authenticationManager, MessageHelper message) {
        this.jwtService = js;
        this.authenticationManager = authenticationManager;
        this.message = message;
    }

    @Override
    public ResponseService<AuthResponse> login(Credentials credentials) {

        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    credentials.username(),
                    credentials.password()
            );
            Authentication auth = authenticationManager.authenticate(authToken);
            String token = jwtService.generateToken(auth);
            return ResponseService.build(
                    BusinessCodes.SUCCESS,
                    message.i18n("AUTHENTICATION.SUCCESS"),
                    new AuthResponse(token, Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli())

            );
        } catch (AuthenticationException e) {
            throw new InvalidCredentials(BusinessCodes.INVALID_CREDENTIALS);
        }
    }
}
