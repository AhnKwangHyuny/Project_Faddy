package faddy.backend.user.service;

import faddy.backend.global.Utils.RedisUtil;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RedisUtil redisUtil;

    public boolean isUserIdDuplicated(final String field , final String value) {

        return switch (field) {
            case "userId" -> userRepository.findUsernameByUsername(value).isPresent();
            case "nickname" -> userRepository.findNicknameByNickname(value).isPresent();
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        };
    }


}
