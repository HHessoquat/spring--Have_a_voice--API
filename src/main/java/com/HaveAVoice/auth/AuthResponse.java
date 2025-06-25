package com.HaveAVoice.auth;

import lombok.Data;

import java.time.Instant;

public record AuthResponse(String token, Long expires) {
}
