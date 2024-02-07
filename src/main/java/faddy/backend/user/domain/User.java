package faddy.backend.user.domain;

import faddy.backend.auth.domain.EmailVerifications;
import faddy.backend.auth.domain.SocialLogin;
import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id" )
    private ProfileImage profileImage;

    @OneToOne(mappedBy = "user")
    private EmailVerifications emailVerifications;

    @OneToOne(mappedBy = "user")
    private SocialLogin socialLogin;

    @Column(name = "user_name", length = 128, unique = true, nullable = false)
    private String userName;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "salt", length = 128, nullable = false)
    private String salt;

    @Column(name = "nickname", length = 20, unique = true)
    private String nickname;

    @Enumerated(value = STRING)
    @Column(name = "user_level", length = 10, nullable = false , columnDefinition = "default 'LEVEL_1'")
    private UserLevel userLevel = UserLevel.LEVEL_1;

    @Enumerated(value = STRING)
    private UserStatus status;



    /*
    *  @@ social login일 때 생성자
    * */
//    public User(final Long id, final String socialLoginId, final String nickname, final String imageUrl) {
//        this.id = id;
//        this.socialLoginId = socialLoginId;
//        this.nickname = nickname;
//        this.lastLoginDate = LocalDateTime.now();
//        this.imageUrl = imageUrl;
//        this.status = ACTIVE;
//        this.createdAt = LocalDateTime.now();
//        this.modifiedAt = LocalDateTime.now();
//    }

    /*
     *  @@ 일반 회원가입 유저 생성자
     * */

    public User(Profile profile, String userName, String passwordHash, String salt, String nickname, UserLevel userLevel, UserStatus status) {
        this.profile = profile;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.nickname = nickname;
        this.userLevel = userLevel;
        this.status = status;
    }
}
