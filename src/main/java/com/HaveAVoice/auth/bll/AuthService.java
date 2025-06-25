package com.HaveAVoice.auth.bll;

import com.HaveAVoice.auth.AuthResponse;
import com.HaveAVoice.auth.Credentials;
import com.HaveAVoice.shared.Response.ResponseService;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

public interface AuthService {
    ResponseService<AuthResponse> login(Credentials credential);
}
