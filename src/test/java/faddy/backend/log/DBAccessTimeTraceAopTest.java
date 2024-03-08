package faddy.backend.log;

import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import faddy.backend.user.domain.UserLevel;
import faddy.backend.user.domain.UserStatus;
import faddy.backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class DBAccessTimeTraceAopTest {

    @Test
    public void whenDBAccessed_thenTimeIsLogged() {

        //given
        Profile profile = new Profile("defaultProfile");


        //when
//        userRepository.save(user);

        //then

    }


}
