package faddy.backend.user.service;

import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isUserIdDuplicated(final String userId) {

        boolean isDuplicated;

        String username = userRepository.findUsernameByUsername(userId).orElse(null);

        log.info("username = " + username );

        isDuplicated = username != null;

        return isDuplicated;
    }

}
