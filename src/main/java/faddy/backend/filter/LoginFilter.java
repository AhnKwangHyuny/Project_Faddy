package faddy.backend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import faddy.backend.auth.dto.CustomUserDetails;
import faddy.backend.auth.dto.LoginRequestDto;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.global.exception.ExceptionCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@PropertySource("classpath:application.yml")
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.jwt.access.expire-time}")
    private Long ACCESS_EXPIRE_TIME;

    @Value("${spring.jwt.refresh.expire-time}")
    private Long REFRESH_EXPIRE_TIME;

    public LoginFilter(AuthenticationManager authenticationManager , JwtUtil jwtUtil ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/v1/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("execute login Filter");

        //클라이언트 요청에서 user id , password 추출
        LoginRequestDto loginRequestDto = null;
        try {
            loginRequestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (IOException e) {
            try {
                unsuccessfulAuthentication(request, response, new AuthenticationServiceException(e.getMessage()));
            } catch (IOException | ServletException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }

        String username = loginRequestDto.getUsernameOrEmail();
        String password = loginRequestDto.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username , password , null);

        // 토큰 검증을 위해 authenticationManager에 token 처리 위임
        return authenticationManager.authenticate(authToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //UserDetailsS
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String username = customUserDetails.getUsername();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            GrantedAuthority auth = iterator.next();
            String role = auth.getAuthority();

            String access = jwtUtil.createJwt("access", username, role, ACCESS_EXPIRE_TIME);

            //응답 설

            log.info("access is : " + access  + "refresh is " + refresh);

            response.addHeader("Authorization", "Bearer " + access);


        } catch (RuntimeException e) {
            throw new faddy.backend.global.exception.AuthenticationException(ExceptionCode.AUTHENTICATION_ERROR);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
