package faddy.backend.auth.jwt.Service;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.JwtValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@PropertySource("classpath:application.yml")
@Component
public class JwtUtil {

    private final SecretKey key;

    private final long ACCESS_TOKEN_EXPIRE_TIME;
    private final long REFRESH_TOKEN_EXPIRE_TIME;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret ,
                   @Value("${spring.jwt.access.expire-time}") long accessExpiredAt,
                   @Value("${spring.jwt.access.expire-time}") long refreshExpiredAt) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        ACCESS_TOKEN_EXPIRE_TIME = accessExpiredAt;
        REFRESH_TOKEN_EXPIRE_TIME = refreshExpiredAt;
    }

    /**
     * Username 및 Authorities 기반 토큰 생성 메소드.
     * @param username
     * @param authorities
     * @return JWT(String)
     */


    public String generate(String subject, Date expiredAt) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createJwt(String category, String username, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("category", category)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key ,SignatureAlgorithm.HS256 )
                .compact();
    }


    /**
     * 사용자가 보낸 요청 헤더의 'Authorization' 필드에서 토큰을 추출하는 메소드.
     * @param request
     * @return token(String)
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }



    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            String username = claims.getBody().getSubject();
            if (username == null || username.isEmpty()) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtValidationException(ExceptionCode.INVALID_JWT_TOKEN);
        }
    }


    private Date getExpireDate(Long expireTime) {
        Date now = new Date();
        return new Date(System.currentTimeMillis() +  expireTime);
    }

    public String getUsername(String accessToken) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }


    public Boolean isExpired(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return claimsJws.getBody().getExpiration().before(new Date());
    }

}
