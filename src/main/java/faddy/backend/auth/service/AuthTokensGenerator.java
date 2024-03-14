package faddy.backend.auth.service;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.auth.jwt.Service.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor

public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 5;	   // 5시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final JwtUtil jwtUtil;

    public AuthTokensResponse generate(String username ) {
        long now = (new Date()).getTime();

        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = username;

        String accessToken = jwtUtil.generate(subject, accessTokenExpiredAt);

        String refreshToken = jwtUtil.generate(subject, refreshTokenExpiredAt);

        return AuthTokensResponse.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public boolean isValidToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String extractUsername(String accessToken) {
        return jwtUtil.getUsername(accessToken);
    }
}