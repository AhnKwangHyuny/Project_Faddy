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

    User save(User user);

    @Modifying
    @Query(
            """
            UPDATE User user
            SET user.status = 'DELETED'
            WHERE user.id = :userId
            """
    )
    void deleteByUserId(@Param("userId") final Long userId);
}

