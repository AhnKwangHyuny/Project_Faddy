package faddy.backend.filter;

import faddy.backend.auth.dto.CustomUserDetails;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.global.exception.ExceptionCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager , JwtUtil jwtUtil ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 user id , password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

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

            String token = jwtUtil.generateToken(username , authorities);

            response.addHeader("Authorization", "Bearer " + token);

        } catch (RuntimeException e) {
            throw new faddy.backend.global.exception.AuthenticationException(ExceptionCode.AUTHENTICATION_ERROR);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
