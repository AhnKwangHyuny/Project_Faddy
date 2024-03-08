package faddy.backend.user.repository;

import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import faddy.backend.user.domain.UserLevel;
import faddy.backend.user.domain.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@DataJpaTest
public class UserRepositoryTests {


    @Autowired
    private UserRepository userRepository;

    private Profile profile;

    @BeforeEach
    public void setUp() {
        profile = new Profile("defaultProfile");
    }

    @Test
    @Transactional
    public void testSaveUser() {
//        User user = new User("ahn" , "dwq" ,  "dwq");
//
//        User savedUser = userRepository.save(user);
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
