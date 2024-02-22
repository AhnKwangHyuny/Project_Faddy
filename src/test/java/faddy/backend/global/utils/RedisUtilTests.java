package faddy.backend.global.utils;

import faddy.backend.global.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RedisUtilTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedisConnection() {
        String key = "testKey";
        String value = "testValue";

        // 데이터 저장
        redisUtil.setData(key, value);

        // 데이터 조회
        String returnValue = redisUtil.getData(key);
        assertEquals(value, returnValue, "Redis에 저장된 값이 일치해야 합니다.");

        // 데이터 삭제
        redisUtil.deleteData(key);

        // 데이터 조회 (삭제된 데이터이므로 null이어야 함)
        assertNull(redisUtil.getData(key), "삭제된 데이터는 null이어야 합니다.");
    }
}
