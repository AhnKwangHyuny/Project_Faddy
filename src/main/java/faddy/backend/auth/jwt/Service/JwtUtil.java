package faddy.backend.auth.jwt.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;
@PropertySource("spring")
@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY;
    private final long EXPIRE_TIME;

    public JwtUtil(@Value("${jwt.secret}") String secret ,@Value("${jwt.token.expire-time}") long expireTime) {
        SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        EXPIRE_TIME =expireTime;
    }

    /**
     * Username 및 Authorities 기반 토큰 생성 메소드.
     * @param username
     * @param authorities
     * @return JWT(String)
     */
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", authorities.stream().findFirst().get().toString())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
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


    /**
     * 토큰으로부터 받은 정보를 기반으로 Authentication 객체를 반환하는 메소드.
     * @param accessToken
     * @return Authentication
     */
    public Authentication getAuthentication(String accessToken) {
        return new UsernamePasswordAuthenticationToken(getUsername(accessToken), "", createAuthorityList(getRole(accessToken)));
    }

    public boolean validateToken(String accessToken) {
        if (accessToken == null) {
            return false;
        }

        try {
            return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .getExpiration()
                .after(new Date());
        }
        catch (Exception e) {
            return false;
        }
    }

    private Date getExpireDate() {
        Date now = new Date();
        return new Date(now.getTime() + EXPIRE_TIME);
    }

    public String getUsername(String accessToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    public String getRole(String accessToken) {
        return (String) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .get("role", String.class);

    }

    public Boolean isExpired(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claimsJws.getBody().getExpiration().before(new Date());
    }

}
