package faddy.backend.auth.service;

import faddy.backend.auth.repository.TokenBlackListRepository;
import faddy.backend.global.exception.AuthorizationException;
import faddy.backend.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenValidator {

    private final AuthTokensGenerator authTokensGenerator;
    private final TokenBlackListRepository tokenBlackListRepository;

    public void validateToken(String refreshToken) {
        if (!authTokensGenerator.isValidToken(refreshToken)) {
            throw new AuthorizationException(ExceptionCode.INVALID_REFRESH_TOKEN);
        }
    }

    public void validateTokenOwnerUsername(String refreshToken, String username) {
        final String ownerUsername = authTokensGenerator.extractUsername(refreshToken);
        if (!ownerUsername.equals(username)) {
            throw new AuthorizationException(ExceptionCode.TOKEN_OWNER_MISMATCH);
        }
    }
}
