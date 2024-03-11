package faddy.backend.auth.handler;

import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.Utils.FormatConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    private final String LOGIN_PATH = "/login";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // 인증이 필요한 URI에 인증 되지않은 사용자가 접근 시 동작할 이벤트 구현
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setHeader("Content-Type", "application/json");
//        response.getWriter().write(FormatConverter.toJson(
//                ResponseDto.response("401" ,
//                        "접근 권한이 없는 회원입니다.")
//
//        ));
//
//        response.getWriter().flush();
//        response.getWriter().close();

        response.sendRedirect(LOGIN_PATH);
    }
}
