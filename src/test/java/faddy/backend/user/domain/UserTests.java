package faddy.backend.user.domain;

import faddy.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@DataJpaTest
public class UserTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private Profile defaultProfile;

    @BeforeEach
    public void setUp() {
        defaultProfile = new Profile("defaultProfile");
        entityManager.persistAndFlush(defaultProfile);
    }

    @Test
    @Transactional
    public void testSaveUser() {
//        User user = new User(defaultProfile , "ahn" , "dwq" ,  "dwq" , "dwqdwq" , UserLevel.LEVEL_1 , UserStatus.ACTIVE);
//
//        User savedUser = entityManager.persistAndFlush(user);
//
//        User foundUser = userRepository.findById((long) savedUser.getUserId()).orElse(null);
//
//        System.out.println("foundUser = " + foundUser);
//
//        assertThat(foundUser).isNotNull();
//
//        assertThat(foundUser.getNickname()).isEqualTo(savedUser.getNickname());
//
//        System.out.println("foundUser.toString() = " + foundUser.toString());
    }
}
