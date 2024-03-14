package faddy.backend.user.domain;

public enum UserLevel {
    LEVEL_1(1, "패션입문자" ,"070A17" ),
    LEVEL_2(2, "패션풋내기" ,"E5B421" ),
    LEVEL_3(3, "패션센스굿" ,"47BD35"),
    LEVEL_4(4, "패션트랜더" ,"4B6BFB"),
    LEVEL_5(5, "인플루언서" ,"FB4B80");

    private final int level;
    private final String description;
    private final String levelUniqueHexColor;

    UserLevel(int level, String description , String levelUniqueHexColor) {
        this.level = level;
        this.description = description;
        this.levelUniqueHexColor = levelUniqueHexColor;
    }

}
