package com.faddy.domain.user;



import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "user_name", length = 128, unique = true, nullable = false)
    private String userName;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name="salt", length=128, nullable=false)
    private String salt;

    @Column(name="nickname", length=20, unique=true)
    private String nickname;

    @Column(name="user_level", length=10, nullable=false)
    private String userLevel;

    // getters and setters
}
