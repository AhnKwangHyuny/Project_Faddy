package faddy.backend.user.service;


import faddy.backend.log.DBAccessTimeTraceAop;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import faddy.backend.user.domain.UserLevel;
import faddy.backend.user.domain.UserStatus;
import faddy.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@DataJpaTest
@Import({UserService.class , DBAccessTimeTraceAop.class}) // UserService를 테스트 컨텍스트에 포함
public class UserServiceTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    @Transactional
    public void setUp() {
        Profile profile = new Profile("defaultProfile");

        entityManager.persistAndFlush(profile);

        User testUser1 = new User(profile , "ahn" , "dwq" ,  "dwq" , "dwqdwq" , UserLevel.LEVEL_1 , UserStatus.ACTIVE);

        userRepository.save(testUser1);
    }

    @Test
    @Transactional
    public void 유저_아이디_중복_테스트() {

        //Given
        String userId = "ahn";

        //when
        boolean idDuplicated = userService.isUserIdDuplicated(userId);

        //then
        assertTrue(idDuplicated);

    }
}
