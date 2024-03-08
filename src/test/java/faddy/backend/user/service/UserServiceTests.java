package faddy.backend.user.service;


import faddy.backend.auth.infrastructure.WebSecurityConfig;
import faddy.backend.global.Utils.RedisUtil;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.log.DBAccessTimeTraceAop;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import faddy.backend.user.domain.UserLevel;
import faddy.backend.user.domain.UserStatus;
import faddy.backend.user.dto.request.SignupInfoDto;
import faddy.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    @Transactional
    public void setUp() {


    }

    @Test
    @Transactional
    public void 유저_아이디_중복_테스트() {

        //Given
        String userId = "ahn";

        //when

        //then

    }

    @Test
    @Transactional
    public void 사용자_가입_테스트() {
        // Given
        SignupInfoDto info = new SignupInfoDto();
        info.setUsername("testUser");
        info.setPassword("testPassword");
        info.setNickname("testNickname");
        info.setEmail("testEmail@test.com");

        // When
        Optional<String> savedUsername = userService.joinUser(info);

        // Then
        assertTrue(savedUsername.isPresent());
        assertEquals("testUser", savedUsername.get());

        // 사용자가 저장되었는지 확인
        Optional<User> savedUser = userRepository.findByUsername("testUser");
        assertTrue(savedUser.isPresent());
        assertEquals("testUser", savedUser.get().getUsername());
        assertEquals("testNickname", savedUser.get().getNickname());
        assertEquals("testEmail@test.com", savedUser.get().getEmail());
    }

    @Test
    @Transactional
    public void 사용자_가입_실패_테스트() {
        // Given
        SignupInfoDto info = new SignupInfoDto();
        info.setUsername("testUser");
        info.setPassword("testPassword");
        info.setNickname("testNickname");
        info.setEmail("testEmail@test.com");

        // 사용자를 먼저 저장
        userService.joinUser(info);

        // 동일한 정보로 다시 가입 시도
        SignupInfoDto duplicateInfo = new SignupInfoDto();
        duplicateInfo.setUsername("testUser");
        duplicateInfo.setPassword("testPassword");
        duplicateInfo.setNickname("testNickname");
        duplicateInfo.setEmail("testEmail@test.com");

        // When & Then
        assertThrows(RuntimeException.class, () -> userService.joinUser(duplicateInfo));
    }


}
