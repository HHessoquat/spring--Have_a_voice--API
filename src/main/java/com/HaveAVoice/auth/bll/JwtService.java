package com.HaveAVoice.auth.bll;

import com.HaveAVoice.User.BLL.UserService;
import com.HaveAVoice.User.UserDB;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    public JwtService(JwtEncoder jwtEncoder, UserService userService) {
        this.jwtEncoder = jwtEncoder;
        this.userService = userService;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        UserDB user = this.userService.getUserByUsername(authentication.getName()).body;
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("HaveAVoice_Corp")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("userId", user.getId())
                .claim("roles", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
