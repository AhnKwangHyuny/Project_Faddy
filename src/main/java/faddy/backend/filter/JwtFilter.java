package faddy.backend.filter;

import faddy.backend.auth.dto.CustomUserDetails;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            log.warn("token null");
            filterChain.doFilter(request, response);

            return;
        }

        // Bear 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        if (token == null) {
            log.warn("token not exists");
            filterChain.doFilter(request, response);
        }

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            log.warn("token expired");
            filterChain.doFilter(request, response);

            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);


        //userEntity를 생성하여 값 set
        User userEntity = new User.Builder()
                .withNickname("mock")
                .withEmail("mock.naver.com")
                .withPassword("")
                .withUsername(username)
                .build();

        CustomUserDetails userDetails = new CustomUserDetails(userEntity);

        // 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);


        filterChain.doFilter(request, response);
    }
}
