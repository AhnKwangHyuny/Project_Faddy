package faddy.backend.user.repository;

import faddy.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    /**
     * 주어진 사용자 이름으로 사용자를 찾는 메소드.
     * @param username 찾고자 하는 사용자의 이름.
     * @return 이름이 일치하는 사용자를 Optional로 감싼 객체. 사용자가 없으면 Optional.empty()를 반환.
     */
    Optional<User> findByUsername(String username);

    @Query("SELECT u.username FROM User u WHERE u.username = ?1")
    Optional<String> findUsernameByUsername(String username);

    @Query("SELECT u.email FROM User u WHERE u.email = ?1")
    Optional<String> findEmailByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query(
            """
            UPDATE User user SET user.status = 'DELETED' WHERE user.id = :userId
            """
    )
    void deleteByUserId(@Param("userId") final Long userId);
}

