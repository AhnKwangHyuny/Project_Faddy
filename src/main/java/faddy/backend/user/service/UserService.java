package faddy.backend.user.service;

import faddy.backend.global.Utils.RedisUtil;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.InternalServerException;
import faddy.backend.user.domain.User;
import faddy.backend.user.dto.request.SignupInfoDto;
import faddy.backend.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RedisUtil redisUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisUtil redisUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisUtil = redisUtil;
    }

    public boolean isUserIdDuplicated(final String field, final String value) {

        return switch (field) {
            case "userId" -> userRepository.findUsernameByUsername(value).isPresent();
            case "nickname" -> userRepository.findNicknameByNickname(value).isPresent();
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        };
    }


    @Transactional
    public Optional<String> joinUser(@Valid final SignupInfoDto info) {

        User savedUser = null;

        try {

            User user = new User.Builder()
                .withUsername(info.getUsername ())
                .withPassword(passwordEncoder.encode(info.getPassword())) // password 암호화 하여 저장
                .withNickname(info.getNickname())
                .withEmail(info.getEmail())
                .build();

            savedUser = userRepository.save(user);


        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage() + ex);
            throw new BadRequestException(ExceptionCode.INVALID_REQUEST);
        }

        if(savedUser == null) {
            throw new InternalServerException(ExceptionCode.NOT_SAVE_USER);
        }

        return Optional.ofNullable(savedUser.getUsername());
    }

}
