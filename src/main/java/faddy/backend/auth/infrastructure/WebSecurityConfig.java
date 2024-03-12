package faddy.backend.auth.infrastructure;

import faddy.backend.auth.handler.CustomAccessDeniedHandler;
import faddy.backend.auth.handler.EntryPointUnauthorizedHandler;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.filter.JwtFilter;
import faddy.backend.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    private final JwtUtil jwtUtil;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    public WebSecurityConfig(AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil,
                             CustomAccessDeniedHandler customAccessDeniedHandler,
                             EntryPointUnauthorizedHandler entryPointUnauthorizedHandler) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(entryPointUnauthorizedHandler);

        //csrf disable
        http
                .csrf((auth) -> auth.disable())
                .cors(corsCustomize -> corsCustomize.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.setAllowedOriginPatterns(Collections.singletonList("*"));
                    config.addAllowedMethod(HttpMethod.OPTIONS);
                    config.addAllowedMethod(HttpMethod.GET);
                    config.addAllowedMethod(HttpMethod.POST);
                    config.addAllowedMethod(HttpMethod.PUT);
                    config.addAllowedMethod(HttpMethod.DELETE);
                    config.addAllowedHeader("*");
                    config.addExposedHeader("*");  // Add this line
                    return config;
                }));

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //경로 권한 설정
        http
                .authorizeHttpRequests((auth) -> auth
                    .anyRequest().permitAll()
                );

        // 필터 설정
        http
                .addFilterBefore(new JwtFilter(jwtUtil) , LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration) , jwtUtil),
                        UsernamePasswordAuthenticationFilter.class);


        //세션 설정
        http
                .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }


}
