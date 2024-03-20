package faddy.backend.application;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import faddy.backend.auth.dto.LoginRequestDto;
import faddy.backend.user.domain.User;
import faddy.backend.user.repository.UserRepository;
import faddy.backend.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@SpringBootTest

public class JwtTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    private User testUser;

    @BeforeEach
    @Transactional
    public void setUp() {
        User userEntity = new User.Builder()
                .withNickname("dwqdwqwq").withAuthority("ROLE_ADMIN").withUsername("agh0314")
                .withPassword(passwordEncoder.encode("agh@p970314")).withEmail("agh0314@naver.com")
                .build();

        testUser = userRepository.save(userEntity);

    }

    @Test
    @Transactional
    public void JWT_로그인_확인() throws Exception {

        //Given
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername(testUser.getUsername());
        loginRequestDto.setPassword("agh@p970314");

        ObjectMapper objectMapper = new ObjectMapper();
        String loginRequestBody = objectMapper.writeValueAsString(loginRequestDto);

        //when
        // 로그인 요청을 시뮬레이션합니다.
        MvcResult result = mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestBody))
                .andReturn();

        int status = result.getResponse().getStatus();

        if (status == 201) {
            System.out.println("Status is Created as expected");
        } else {
            System.out.println("Unexpected status: " + status);

            //then

            // 응답 헤더에서 JWT 토큰을 가져옵니다.
            String jwtToken = result.getResponse().getHeader("Authorization");

            // JWT 토큰을 콘솔에 출력합니다.
            System.out.println("JWT Token: " + jwtToken);
        }
    }
}
