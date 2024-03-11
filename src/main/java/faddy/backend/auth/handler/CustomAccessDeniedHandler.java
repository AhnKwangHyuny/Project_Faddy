package faddy.backend.auth.handler;
import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.Utils.FormatConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", "applicaiton/json");
        response.getWriter().write(FormatConverter.toJson(
                ResponseDto.response(
                        "401",
                        "접근 권한이 없습니다."
                )
        ));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
