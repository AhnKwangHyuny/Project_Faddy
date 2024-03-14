//package faddy.backend.auth.jwt.infrastruture;
//
//import faddy.backend.auth.jwt.Service.TokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//// 커스텀된 TokenProvider 와 JwtFilter 를 SecurityConfig 에 적용
//@RequiredArgsConstructor
////@Configuration
//public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain , HttpSecurity>   {
//
//    private final TokenProvider tokenProvider;
//
//    // TokenProvider 를 주입받아서 JwtFilter 를 통해 Security 로직에 필터를 등록
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        JwtFilter customFilter = new JwtFilter(tokenProvider);
//        http.addFilterBefore(customFilter , UsernamePasswordAuthenticationFilter.class);
//    }
//}
