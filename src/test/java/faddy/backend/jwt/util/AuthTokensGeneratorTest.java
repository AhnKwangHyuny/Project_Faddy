package faddy.backend.jwt.util;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.auth.service.AuthTokensGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest
class AuthTokensGeneratorTest {


    @Autowired
    private AuthTokensGenerator authTokensGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void 다중_토큰_응답_생성_테스트() {
        // given
        String username = "testUser";

        // when
        AuthTokensResponse tokens = authTokensGenerator.generate(username);
        String accessToken = tokens.getAccessToken();
        String refreshToken = tokens.getRefreshToken();

        System.out.println(tokens.toString());

        //then
        assertTrue(authTokensGenerator.isValidToken(accessToken), "생성된 Access Token이 유효해야 합니다.");
        assertTrue(authTokensGenerator.isValidToken(refreshToken), "생성된 Refresh Token이 유효해야 합니다.");
        assertEquals(username, authTokensGenerator.extractUsername(accessToken), "Access Token에서 추출한 사용자 이름이 일치해야 합니다.");
    }
}
